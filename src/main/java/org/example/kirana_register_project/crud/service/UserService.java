package org.example.kirana_register_project.crud.service;

import org.example.kirana_register_project.crud.exception.UserNotFoundException;
import org.example.kirana_register_project.crud.model.Users;
import org.example.kirana_register_project.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Business logic to get a user by ID
    public Users getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    // Business logic to create a new user
    public Users createUser(Users user) {
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return userRepository.save(user);
    }

    // Business logic to update user information
    public Users updateUser(UUID userId, Users updatedUser) {
        // Check if the user exists
        Users existingUser = getUserById(userId);
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        return userRepository.save(existingUser);
    }

    // Business logic to delete a user
    public void deleteUser(UUID userId) {
        getUserById(userId);
        userRepository.deleteById(userId);
    }
}
