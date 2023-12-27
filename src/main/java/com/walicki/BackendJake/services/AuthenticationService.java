package com.walicki.BackendJake.services;

import com.walicki.BackendJake.dto.*;
import com.walicki.BackendJake.models.UserEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface AuthenticationService {

    UserEntity signup(RegisterDto registerDto);

    JwtAuthenticationResponse login(LoginDto loginDto);

    UserEntity saveUser(UserDto userDto);

    JwtAuthenticationResponse refreshToken(RefreshTokenDto refreshTokenDto);
}
