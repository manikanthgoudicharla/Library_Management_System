package com.lms.lms.controller.user;

import com.lms.lms.dto.UserInputDTO;
import com.lms.lms.dto.UserResponseDTO;
import com.lms.lms.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //  Create User
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserInputDTO dto) {
        UserResponseDTO createdUser = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    //  Get All Users
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //  Get User By Id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        Optional<UserResponseDTO> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // Update User
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable String id,
            @RequestBody UserInputDTO dto) {

        Optional<UserResponseDTO> updatedUser = userService.updateUser(id, dto);
        return updatedUser.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    // Delete User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
