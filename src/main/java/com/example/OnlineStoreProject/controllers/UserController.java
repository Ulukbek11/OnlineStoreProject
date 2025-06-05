package com.example.OnlineStoreProject.controllers;

import com.example.OnlineStoreProject.dto.UserDto;
import com.example.OnlineStoreProject.models.User;
import com.example.OnlineStoreProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.login(user);
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDto>>  getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> update(@RequestBody Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
