package com.walicki.BackendJake.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity {

    // User id
    @Id
    private String id;

    // Name
    @Setter
    private String username;

    // Password
    @Setter
    @Getter
    private String password;

    // Highscores
    @Setter
    private List<Integer> highscores;

    // Snake color
    @Setter
    @Getter
    private String snake_color;

    // Coffees drank
    @Setter
    private Integer coffees_drank;

}
