package com.walicki.BackendJake.services;

import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


// BUSINESS LOGIC GOES HERE
@Service
public class UserService {

    // Repo
    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get user data
    public Optional<UserEntity> findUser(String username, String password) {
        Optional<UserEntity> userFound = userRepository.findByUsername(username);

        // Using encrypted after sign up logic created
        // String encryptedPassword = passwordEncoder.encode(password);

        // Get user password (should be encoded after registering)
        AtomicReference<String> userPass = new AtomicReference<>("");
        userFound.ifPresent(user -> {
            userPass.set(user.getPassword());
        });

        // Print password for testing purposes
        System.out.println(password);

        // return user's data or null
        if(Objects.equals(userPass.get(), password)){
            return userFound;
        } else return Optional.empty();
    }

    // Register new user
    public UserEntity registerNewUser(RegisterDto userDto) {
        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new RuntimeException("This username already exists. Please Choose other one or Log In.");
        }

        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
        UserEntity newUserEntity = new UserEntity();
        newUserEntity.setUsername(userDto.getUsername());
        newUserEntity.setPassword(encryptedPassword);

        // Starting user statistics
        newUserEntity.setHighscores(new ArrayList<Integer>());
        newUserEntity.setCoffees_drank(0);

        return userRepository.save(newUserEntity);
    }
}
