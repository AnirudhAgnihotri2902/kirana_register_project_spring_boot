package org.example.kirana_register_project.crud.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    // You can add more constructors or custom logic if needed
}
