package pl.uncleglass.littlereddit.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.domain.Vote;
import pl.uncleglass.littlereddit.repositories.VoteRepository;
import pl.uncleglass.littlereddit.services.LinkService;

import java.util.Optional;

@RestController
public class VoteController {

    private VoteRepository voteRepository;
    private LinkService linkService;

    public VoteController(VoteRepository voteRepository, LinkService linkService) {
        this.voteRepository = voteRepository;
        this.linkService = linkService;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("vote/link/{linkId}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int voteCount) {
        Optional<Link> optionalLink = linkService.get(linkId);
        if (optionalLink.isPresent()) {
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteRepository.save(vote);

            int updatedVoteCount = voteCount + direction;
            link.setVoteCount(updatedVoteCount);
            linkService.update(link);//TODO check whether this line is necessary
            return updatedVoteCount;
        }
        return voteCount;
    }
}
