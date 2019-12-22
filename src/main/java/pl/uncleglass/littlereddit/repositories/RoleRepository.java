package pl.uncleglass.littlereddit.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.uncleglass.littlereddit.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
