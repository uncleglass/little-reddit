package pl.uncleglass.littlereddit.services;

import org.springframework.stereotype.Service;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.repositories.LinkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public List<Link> getAll() {
        return linkRepository.findAll();
    }

    public Optional<Link> get(Long id) {
        return linkRepository.findById(id);
    }

    public void add(Link link) {
        linkRepository.save(link);
    }

    public void update(Link link) {
        linkRepository.save(link);
    }
}
