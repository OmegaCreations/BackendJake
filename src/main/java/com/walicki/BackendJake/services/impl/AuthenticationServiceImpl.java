package com.walicki.BackendJake.services.impl;

import com.walicki.BackendJake.dto.JwtAuthenticationResponse;
import com.walicki.BackendJake.dto.LoginDto;
import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.Role;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import com.walicki.BackendJake.services.AuthenticationService;
import com.walicki.BackendJake.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    // Create new user entity
    public UserEntity signup(RegisterDto registerDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setUsername(registerDto.getUsername());
        userEntity.setRole(Role.USER);
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));

        // Other data
        userEntity.setHighscores(Collections.singletonList(0));
        userEntity.setSnake_color("#10b981");
        userEntity.setCoffees_drank(0);

        return userRepository.save(userEntity);
    }

    public JwtAuthenticationResponse login(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        var user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

}