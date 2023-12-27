package com.walicki.BackendJake;

import com.walicki.BackendJake.models.Role;
import com.walicki.BackendJake.models.UserEntity;
import com.walicki.BackendJake.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class 	BackendJakeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BackendJakeApplication.class, args);
	}

	public void run(String... args) {
		Optional<UserEntity> adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount.isEmpty()) {
			UserEntity user = new UserEntity();
			user.setUsername("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}
}
