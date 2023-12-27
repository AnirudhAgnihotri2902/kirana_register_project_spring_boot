package org.example.kirana_register_project.crud.service;

import org.example.kirana_register_project.crud.exception.UserNotFoundException;
import org.example.kirana_register_project.crud.model.Users;
import org.example.kirana_register_project.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserById() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Users user = new Users();
        when(userRepository.findById(userId));

        // Act
        Users result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId));

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testCreateUser() {
        // Arrange
        Users user = new Users();
        when(userRepository.save(user)).thenReturn(user);

        // Act
        Users result = userService.createUser(user);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCreatedAt());
        assertNotNull(result.getUpdatedAt());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Users existingUser = new Users();
        existingUser.setId(userId);
        Users updatedUser = new Users();
        updatedUser.setFirstName("John");
        updatedUser.setLastName("Doe");
        when(userRepository.findById(userId));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        Users result = userService.updateUser(userId, updatedUser);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testUpdateUserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Users updatedUser = new Users();

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(userId, updatedUser));
    }

    @Test
    void testDeleteUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Users user = new Users();
        when(userRepository.findById(userId));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId));

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(userId));
    }
}

