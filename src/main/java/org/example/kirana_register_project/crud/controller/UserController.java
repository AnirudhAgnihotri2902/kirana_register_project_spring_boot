package org.example.kirana_register_project.crud.controller;

import java.util.*;


import org.example.kirana_register_project.crud.exception.ResourceNotFoundException;
import org.example.kirana_register_project.crud.model.Users;
import org.example.kirana_register_project.crud.repository.UserRepository;
import org.example.kirana_register_project.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public Users getuserById(@PathVariable(value = "id") UUID userId)
			throws ResourceNotFoundException {
        return userRepository.findById(userId);

	}

	@PostMapping("/users")
	public Users createuser(@Valid @RequestBody Users user) {
		return userService.createUser(user);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Users> updateuser(@PathVariable(value = "id") UUID userId,
			@Valid @RequestBody Users userDetails) throws ResourceNotFoundException {
		Users user = userRepository.findById(userId);

		user.setEmailId(userDetails.getEmailId());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(userDetails.getFirstName());
		user.setCreatedAt(user.getCreatedAt());
		user.setUpdatedAt(user.getUpdatedAt());
		final Users updateduser = userRepository.save(user);
		return ResponseEntity.ok(updateduser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteuser(@PathVariable(value = "id") UUID userId)
			throws ResourceNotFoundException {
		Users user = userRepository.findById(userId);

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
