package pl.uncleglass.littlereddit.services;

import org.springframework.stereotype.Service;
import pl.uncleglass.littlereddit.domain.Role;
import pl.uncleglass.littlereddit.repositories.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
