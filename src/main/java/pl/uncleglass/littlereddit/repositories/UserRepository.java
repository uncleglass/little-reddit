package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndActivationCode(String email, String activationCode);
}
