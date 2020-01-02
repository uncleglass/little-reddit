package pl.uncleglass.littlereddit.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uncleglass.littlereddit.configuration.AuditorAwareImpl;
import pl.uncleglass.littlereddit.domain.Comment;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.domain.User;
import pl.uncleglass.littlereddit.domain.dtos.LinkRequest;
import pl.uncleglass.littlereddit.services.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LinkController {

    private LinkService linkService;
    private CommentService commentService;
    private UserService userService;
    private Mapper mapper;

    public LinkController(LinkService linkService,
                          CommentService commentService,
                          UserService userService,
                          Mapper mapper) {
        this.linkService = linkService;
        this.commentService = commentService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("linkList", linkService.getAll());
        return "index";
    }

    @GetMapping("/links/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkService.get(id);
        if (link.isPresent()) {
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);
            model.addAttribute("link", currentLink);
            model.addAttribute("comment", comment);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/links/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link", new LinkRequest());
        return "submit";
    }

    @PostMapping("/links/submit")
    public String createLink(LinkRequest linkRequest, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("link", linkRequest);
            return "submit";
        } else {
            User currentUser = null;
            Optional<String> currentAuditor = BeanUtil.getBean(AuditorAwareImpl.class).getCurrentAuditor();
            if (currentAuditor.isPresent()) {
                Optional<User> user = userService.get(currentAuditor.get());
                if (user.isPresent()) {
                    currentUser = user.get();
                }
            }
            Link link = mapper.mapToLink(linkRequest);
            link.setUser(currentUser);
            linkService.add(link);
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
            commentService.add(comment);
        }
        return "redirect:/links/" + comment.getLink().getId();
    }

}
