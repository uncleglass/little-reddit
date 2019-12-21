package pl.uncleglass.littlereddit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.repositories.LinkRepository;

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

//    @PostMapping("/")
//    public Link create(@ModelAttribute Link link) {
//        return linkRepository.save(link);
//    }
//
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
