package com.markiewicz.recipes.user;

import com.markiewicz.recipes.exception.UserExistsException;
import com.markiewicz.recipes.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public void registerUser(User user) {
        userRepository
                .findByEmail(user.getEmail())
                .orElseThrow(UserExistsException::new);

        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
