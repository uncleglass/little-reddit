package pl.uncleglass.littlereddit.services;

import org.springframework.stereotype.Service;
import pl.uncleglass.littlereddit.domain.Comment;
import pl.uncleglass.littlereddit.repositories.CommentRepository;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public void add(Comment comment) {
        commentRepository.save(comment);
    }
}
