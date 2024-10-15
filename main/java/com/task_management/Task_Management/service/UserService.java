package com.task_management.Task_Management.service;

import com.task_management.Task_Management.entity.UserEntity;
import com.task_management.Task_Management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Generate the next sequential ID
    private String generateId() {
        // Fetch the latest task (if exists) based on ID
        Optional<UserEntity> latestTask = userRepository.findTopByOrderByIdDesc();
        if (latestTask.isPresent()) {
            // Increment the ID from the latest task
            int newId = Integer.parseInt(latestTask.get().getId()) + 1;
            return String.valueOf(newId);
        } else {
            // If no tasks exist, start with ID 1
            return "1";
        }
    }

    //To Create new Task
    public UserEntity createTask(UserEntity userEntity){
        if (userEntity.getId() == null) {
            userEntity.setId(generateId()); // Auto-generate ObjectId and set it as String ID
        }
        return userRepository.save(userEntity);
    }

    //Method to Display Task From Database
    public Page<UserEntity> displayAllTask(Pageable pageable){
        return  userRepository.findAll(pageable);
    }

    //Method to find Task by ID
    public Optional<UserEntity> displayTaskById(String id){
        return userRepository.findById(id);
    }

    //Method to updateTask by I'D
    public UserEntity updateTaskById(String id, UserEntity userEntity) {
        // Retrieve the existing task from the database
        Optional<UserEntity> taskExist = userRepository.findById(id);

        // Check if the task exists
        if (!taskExist.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id);
        }
        UserEntity existingTask = taskExist.get();

        // Update only the fields that are present in the request body
        if (userEntity.getTitle() != null) {
            existingTask.setTitle(userEntity.getTitle());
        }
        if (userEntity.getDescription() != null) {
            existingTask.setDescription(userEntity.getDescription());
        }
        if (userEntity.getStatus() != null) {
            existingTask.setStatus(userEntity.getStatus());
        }
        existingTask.setUpdatedDate(LocalDate.now()); // Always update the updatedDate

        // Save the updated task back to the database
        return userRepository.save(existingTask);
    }
    // Deletes a task by its ID if it exists; throws exception otherwise
    public void deleteTask(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}