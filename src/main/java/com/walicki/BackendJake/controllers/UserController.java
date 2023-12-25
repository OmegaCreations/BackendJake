package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.services.UserService;
import jakarta.servlet.annotation.MultipartConfig;
import org.apache.catalina.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Request user registering
    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody RegisterDto userDto) {
        return userService.registerNewUser(userDto);
    }

    // Get single user data for tests
    @GetMapping("/{username}")
    public ResponseEntity<Optional<UserEntity>> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return new ResponseEntity<Optional<UserEntity>>(userService.findUser(username, password), HttpStatus.OK);
    }

    // Save existing username new data
    @PostMapping(value="/save/{username}")
    public ResponseEntity<Optional<UserEntity>> saveUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("snake_color") String snake_color){
        return new ResponseEntity<Optional<UserEntity>>(userService.updateUser(username, password, snake_color), HttpStatus.OK);
    }

}
