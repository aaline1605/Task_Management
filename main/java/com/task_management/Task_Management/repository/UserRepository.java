package com.task_management.Task_Management.repository;

import com.task_management.Task_Management.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity,String> {

    // Method for findById
    Optional<UserEntity> findById(String id);

    // Retrieve the most recently created task (based on ID) for generating sequential IDs
    Optional<UserEntity> findTopByOrderByIdDesc();
}
