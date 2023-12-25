package com.walicki.BackendJake.repositories;

import com.walicki.BackendJake.models.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByUsername(String username); // Look in database by username
}
