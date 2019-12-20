package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.Link;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {

    List<Link> findAll();
}
