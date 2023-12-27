package com.walicki.BackendJake.services.impl;

import com.walicki.BackendJake.dto.*;
import com.walicki.BackendJake.models.Role;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import com.walicki.BackendJake.services.AuthenticationService;
import com.walicki.BackendJake.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
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

    public JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto) {
        String userName = jwtService.extractUserName(refreshTokenDto.getToken());

        UserEntity user = userRepository.findByUsername(userName).orElseThrow();

        if(jwtService.isTokenValid(refreshTokenDto.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenDto.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }


    // Save user data
    public UserEntity saveUser(UserDto userDto) {
        String username = jwtService.extractUserName(userDto.getToken());
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        user.setSnake_color(userDto.getSnake_color());
        return userRepository.save(user);
    }

}
