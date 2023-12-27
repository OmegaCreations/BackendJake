package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import com.walicki.BackendJake.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    UserRepository userRepository;

    private final JWTService jwtService;

    @GetMapping
    // Attribute passed from JwtAuthenticationFilter
    public ResponseEntity<Optional<UserEntity>> adminData(@RequestAttribute String userToken) {
        String userName = jwtService.extractUserName(userToken);
        Optional<UserEntity> admin = userRepository.findByUsername(userName);

        return ResponseEntity.ok(admin);
    }
}
