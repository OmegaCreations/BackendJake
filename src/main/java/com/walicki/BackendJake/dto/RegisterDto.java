package com.walicki.BackendJake.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class RegisterDto {
    private String username;
    private String password;
}
