package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
