package com.walicki.BackendJake.repositories;

import com.walicki.BackendJake.models.Role;
import com.walicki.BackendJake.models.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username); // Look in database by username

    Optional<UserEntity> findByRole(Role role);
}
