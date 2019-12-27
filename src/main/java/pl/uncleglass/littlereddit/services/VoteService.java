package pl.uncleglass.littlereddit.services;

import org.springframework.stereotype.Service;
import pl.uncleglass.littlereddit.domain.Vote;
import pl.uncleglass.littlereddit.repositories.VoteRepository;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void add(Vote vote) {
        voteRepository.save(vote);
    }
}
