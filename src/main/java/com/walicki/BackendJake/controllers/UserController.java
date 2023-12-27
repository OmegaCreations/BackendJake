package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import com.walicki.BackendJake.services.JWTService;
import com.walicki.BackendJake.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final JWTService jwtService;

    @GetMapping
    // Attribute passed from JwtAuthenticationFilter
    public ResponseEntity<Optional<UserEntity>> userData(@RequestAttribute String userToken) {
        String userName = jwtService.extractUserName(userToken);
        Optional<UserEntity> user = userRepository.findByUsername(userName);

        return ResponseEntity.ok(user);
    }


//
//    // Save existing username new data
//    @PostMapping(value="/save/{username}")
//    public ResponseEntity<Optional<UserEntity>> saveUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("snake_color") String snake_color){
//        return new ResponseEntity<Optional<UserEntity>>(userService.updateUser(username, password, snake_color), HttpStatus.OK);
//    }

}
