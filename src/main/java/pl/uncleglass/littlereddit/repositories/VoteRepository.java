package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
