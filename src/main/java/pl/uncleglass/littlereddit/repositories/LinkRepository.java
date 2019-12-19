package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.Link;

public interface LinkRepository extends CrudRepository<Link, Long> {
}
