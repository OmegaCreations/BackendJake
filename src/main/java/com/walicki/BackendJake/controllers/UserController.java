package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
