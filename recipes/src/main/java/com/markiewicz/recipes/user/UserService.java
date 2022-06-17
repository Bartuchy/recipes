package com.markiewicz.recipes.user;

import com.markiewicz.recipes.exception.UserExistsException;
import com.markiewicz.recipes.exception.UserNotFoundException;
import com.markiewicz.recipes.role.Role;
import com.markiewicz.recipes.role.RoleRepository;
import com.markiewicz.recipes.user.dto.UserRegisterDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void registerUser(UserRegisterDto userDto) {
        Optional<User> foundUserWithEmail = userRepository.findByEmail(userDto.getEmail());
        Optional<User> foundUserWithUsername = userRepository.findByUsername(userDto.getUsername());

        if (foundUserWithEmail.isPresent() || foundUserWithUsername.isPresent()) {
            throw new UserExistsException(userDto);
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User(userDto, encodedPassword);

        user.setRoles(List.of(new Role("ROLE_USER")));
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

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findUserByEmail(email);
    }
}
