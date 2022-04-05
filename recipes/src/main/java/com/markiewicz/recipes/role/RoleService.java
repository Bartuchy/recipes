package com.markiewicz.recipes.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
}
