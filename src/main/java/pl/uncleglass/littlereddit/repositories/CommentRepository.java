package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
