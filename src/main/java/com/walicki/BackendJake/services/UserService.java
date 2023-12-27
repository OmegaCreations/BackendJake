package com.walicki.BackendJake.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;


public interface UserService {

    public UserDetailsService userDetailsService();
}
