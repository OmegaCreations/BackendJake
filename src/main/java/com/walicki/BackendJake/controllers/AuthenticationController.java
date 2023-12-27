package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.dto.*;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authenticationService.signup(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestParam("username") String username, @RequestParam("password") String password) {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(username);
        loginDto.setPassword(password);
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }

    @PostMapping("/save")
    public ResponseEntity<UserEntity> saveUser(@RequestParam("token") String token, @RequestParam("snake_color") String snake_color) {
        UserDto user = new UserDto();
        user.setToken(token);
        user.setSnake_color(snake_color);
        return ResponseEntity.ok(authenticationService.saveUser(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestParam("token") String token) {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setToken(token);
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenDto));
    }
}
