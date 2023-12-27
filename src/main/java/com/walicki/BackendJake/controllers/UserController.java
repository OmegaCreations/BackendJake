package com.walicki.BackendJake.controllers;

import com.walicki.BackendJake.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    // Request user registering
//    @PostMapping("/register")
//    public UserEntity registerUser(@RequestBody RegisterDto userDto) {
//        return userService.registerNewUser(userDto);
//    }

    // Get single user data for tests
//    @GetMapping("/{username}")
//    public ResponseEntity<Optional<UserEntity>> getUser(@RequestParam("username") String username, @RequestParam("password") String password){
//        return new ResponseEntity<Optional<UserEntity>>(userService.findUser(username, password), HttpStatus.OK);
//    }
//
//    // Save existing username new data
//    @PostMapping(value="/save/{username}")
//    public ResponseEntity<Optional<UserEntity>> saveUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("snake_color") String snake_color){
//        return new ResponseEntity<Optional<UserEntity>>(userService.updateUser(username, password, snake_color), HttpStatus.OK);
//    }

}
