package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.dto.JwtAuthenticationResponse;
import com.walicki.BackendJake.dto.LoginDto;
import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authenticationService.signup(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authenticationService.login(loginDto));
    }
}
