package com.task_management.Task_Management.controller;

import com.task_management.Task_Management.entity.UserEntity;
import com.task_management.Task_Management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class UserSwaggerController {

    @Autowired
    private UserService userService;

    // Endpoint to save a new task via Swagger UI
    @PostMapping("/tasks")
    public ResponseEntity<UserEntity> saveTask(@RequestBody UserEntity userEntity) {
        UserEntity savedTask = userService.createTask(userEntity);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // Endpoint to retrieve all tasks with pagination parameters
    @GetMapping("/tasks")
    public ResponseEntity<Page<UserEntity>> displayAllTasks(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10")int size) {
        Pageable pageable= PageRequest.of(page,size);
        Page<UserEntity> tasks = userService.displayAllTask(pageable);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no tasks found
        }
        return ResponseEntity.ok(tasks);
    }

    // Endpoint to fetch a task by ID
    @GetMapping("/tasks/{id}")
    public ResponseEntity<UserEntity> getTaskById(@PathVariable String id) {
        return userService.displayTaskById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id));
    }

    // Endpoint to update a task by ID using Swagger UI
    @PutMapping("/tasks/{id}")
    public UserEntity updateStaffById(@PathVariable String id,@RequestBody UserEntity user){
        return userService.updateTaskById(id,user);
    }

    // Endpoint to delete a task by ID
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        userService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
