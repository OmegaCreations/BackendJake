package com.walicki.BackendJake.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class RegisterDto {
    @Getter
    private String username;
    @Getter
    private String password;
}
