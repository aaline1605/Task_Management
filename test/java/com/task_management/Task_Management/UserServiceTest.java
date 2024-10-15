package com.task_management.Task_Management;

import com.task_management.Task_Management.entity.UserEntity;
import com.task_management.Task_Management.entity.Status;
import com.task_management.Task_Management.repository.UserRepository;
import com.task_management.Task_Management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserEntity userEntity;

    // Initialize test data before each test case
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setTitle("Test Task");
        userEntity.setDescription("Test Description");
        userEntity.setStatus(Status.PENDING); // Use the Status enum
        userEntity.setUpdatedDate(LocalDate.now());
    }

    // Test case for creating a new task
    @Test
    void createTask_ShouldReturnSavedTask() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserEntity savedTask = userService.createTask(userEntity);

        assertNotNull(savedTask);
        assertEquals("Test Task", savedTask.getTitle());
        verify(userRepository, times(1)).save(userEntity);
    }

    // Test case for retrieving all tasks
    @Test
    void displayAllTask_ShouldReturnPagedTasks() {
        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(userEntity)));

        Page<UserEntity> tasks = userService.displayAllTask(Pageable.unpaged());

        assertEquals(1, tasks.getTotalElements());
        assertEquals("Test Task", tasks.getContent().get(0).getTitle());
    }

    // Test case for retrieving a task by ID
    @Test
    void displayTaskById_ShouldReturnTask_WhenFound() {
        when(userRepository.findById("1")).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> task = userService.displayTaskById("1");

        assertTrue(task.isPresent());
        assertEquals("Test Task", task.get().getTitle());
    }

    // Test case for update failure due to non-existing task
    @Test
    void updateTaskById_ShouldThrowException_WhenNotFound() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            userService.updateTaskById("2", userEntity);
        });

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode()); // Change here
        assertEquals("Task not found with ID: 2", thrown.getReason());
    }

    // Test case for successful deletion
    @Test
    void deleteTask_ShouldNotThrowException_WhenTaskExists() {
        when(userRepository.existsById("1")).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteTask("1"));
        verify(userRepository, times(1)).deleteById("1");
    }

    // Test case for deletion failure due to non-existing task
    @Test
    void deleteTask_ShouldThrowException_WhenTaskNotFound() {
        when(userRepository.existsById("2")).thenReturn(false);

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            userService.deleteTask("2");
        });

        assertEquals(HttpStatus.NOT_FOUND, thrown.getStatusCode()); // Change here
        assertEquals("Task not found with ID: 2", thrown.getReason());
    }
}
