package com.example.template.core.application.controller;

import com.example.template.core.presentation.model.LoginResponse;
import com.example.template.core.presentation.presenter.UserPresenter;
import com.example.template.core.presentation.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public final class UserController {

    private final UserPresenter userPresenter;

    public UserController(final UserPresenter userPresenter) {
        this.userPresenter = userPresenter;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userPresenter.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable final UUID userId) {
        return userPresenter.getUserById(userId);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody final UserDTO userDTO) {
        return userPresenter.saveUser(userDTO);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable final UUID userId) {
        return userPresenter.deleteUser(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam final String email, @RequestParam final String password) {
        return userPresenter.login(email, password);
    }
}
