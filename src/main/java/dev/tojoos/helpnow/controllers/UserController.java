package dev.tojoos.helpnow.controllers;

import dev.tojoos.helpnow.model.User;
import dev.tojoos.helpnow.services.UserService;
import dev.tojoos.helpnow.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User foundUser = userService.getById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User addedUser = userService.add(user);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
