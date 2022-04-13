package com.markiewicz.recipes.role;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/add")
    public Role addNewRole(@RequestBody Role role) {
        return roleService.addNewRole(role);
    }
}
