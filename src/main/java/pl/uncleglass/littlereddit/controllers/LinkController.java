package pl.uncleglass.littlereddit.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uncleglass.littlereddit.domain.Comment;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.repositories.CommentRepository;
import pl.uncleglass.littlereddit.repositories.LinkRepository;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LinkController {

    private LinkRepository linkRepository;
    private CommentRepository commentRepository;

    public LinkController(LinkRepository linkRepository, CommentRepository commentRepository) {
        this.linkRepository = linkRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("linkList", linkRepository.findAll());
        return "index";
    }

    @GetMapping("/links/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("link", currentLink);
            model.addAttribute("comment", comment);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link"; //TODO finish this view
        } else {
            return "redirect:/links";
        }
    }

    @GetMapping("/links/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "submit";
    }

    @PostMapping("/links/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("link", link);
            return "submit";
        } else {
            linkRepository.save(link);
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/links/{id}";
        }
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/links/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            commentRepository.save(comment);
        }
        return "redirect:/links/" + comment.getLink().getId();
    }

//
//    @PutMapping("/links/{id}")
//    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
//        //get the id
//        return linkRepository.save(link);
//    }
//
//    @DeleteMapping("/links/{id}")
//    public void delete(@PathVariable Long id) {
//        linkRepository.deleteById(id);
//    }
}
