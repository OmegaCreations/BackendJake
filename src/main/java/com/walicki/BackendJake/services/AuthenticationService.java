package com.walicki.BackendJake.services;

import com.walicki.BackendJake.dto.JwtAuthenticationResponse;
import com.walicki.BackendJake.dto.LoginDto;
import com.walicki.BackendJake.dto.RegisterDto;
import com.walicki.BackendJake.models.UserEntity;

public interface AuthenticationService {

    UserEntity signup(RegisterDto registerDto);

    JwtAuthenticationResponse login(LoginDto loginDto);
}
