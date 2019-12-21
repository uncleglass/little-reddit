package pl.uncleglass.littlereddit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.repositories.LinkRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/links")
public class LinkController {

    private LinkRepository linkRepository;

    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("linkList", linkRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkRepository.findById(id);
        if (link.isPresent()) {
            model.addAttribute("link", link.get());
            return "link"; //TODO finish this view
        } else {
            return "redirect:/links";
        }
    }

    @GetMapping("/submit")
    public String newLinkForm(Model model) {
        model.addAttribute("link",new Link());
        return "submit";
    }

    @PostMapping("/submit")
    public String createLink(@Valid Link link, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        return "submit";
    }

//
//    @PutMapping("/{id}")
//    public Link update(@PathVariable Long id, @ModelAttribute Link link) {
//        //get the id
//        return linkRepository.save(link);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        linkRepository.deleteById(id);
//    }
}
