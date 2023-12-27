package com.walicki.BackendJake.services.impl;

import com.walicki.BackendJake.repositories.UserRepository;
import com.walicki.BackendJake.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


// BUSINESS LOGIC GOES HERE
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Repo
    @Autowired
    @Lazy // Break bean cycle
    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User nor found!"));
            }
        };
    }

//
//    // Constructor
//    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    // Get user data
//    public Optional<UserEntity> findUser(String username, String password) {
//        Optional<UserEntity> userFound = userRepository.findByUsername(username);
//
//        // Using encrypted after sign up logic created
//        // String encryptedPassword = passwordEncoder.encode(password);
//
//        // Get user password (should be encoded after registering)
//        AtomicReference<String> userPass = new AtomicReference<>("");
//        userFound.ifPresent(user -> {
//            userPass.set(user.getPassword());
//        });
//
//        // return user's data or null
//        if(Objects.equals(userPass.get(), password)){
//            return userFound;
//        } else return Optional.empty();
//    }
//
//    // Update user
//    public Optional<UserEntity> updateUser(String username, String password, String snake_color) {
//        Optional<UserEntity> userFound =  findUser(username, password);
//        userFound.ifPresent(user -> {
//            user.setSnake_color(snake_color);
//            userRepository.save(user);
//        });
//
//        return Optional.empty();
//    }
//
//    // Register new user
//    public UserEntity registerNewUser(RegisterDto userDto) {
//        if(userRepository.findByUsername(userDto.getUsername()).isPresent()) {
//            throw new RuntimeException("This username already exists. Please Choose other one or Log In.");
//        }
//
//        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
//        UserEntity newUserEntity = new UserEntity();
//        newUserEntity.setUsername(userDto.getUsername());
//        newUserEntity.setPassword(encryptedPassword);
//
//        // Starting user statistics
//        newUserEntity.setHighscores(new ArrayList<Integer>());
//        newUserEntity.setCoffees_drank(0);
//
//        return userRepository.save(newUserEntity);
//    }
}
