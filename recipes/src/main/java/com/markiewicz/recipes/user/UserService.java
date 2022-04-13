package com.markiewicz.recipes.user;

import com.markiewicz.recipes.exception.UserExistsException;
import com.markiewicz.recipes.exception.UserNotFoundException;
import com.markiewicz.recipes.role.Role;
import com.markiewicz.recipes.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if (foundUser.isPresent()) {
            throw new UserExistsException(user);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(new Role(null, "ROLE_USER")));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public void addRoleToUser(String email, String roleName) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(roleName).orElseThrow(); //TODO: RoleNotFoundException
        user.getRoles().add(role);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
