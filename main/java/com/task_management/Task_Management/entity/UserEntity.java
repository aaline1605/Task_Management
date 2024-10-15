package com.task_management.Task_Management.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "task_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

     // Unique identifier for the task (auto-generated, read-only)
     @Id
     @Schema(description = "Auto-generated ID (Read-only)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
     private String id;

     // Mandatory field for task title
     @NotBlank(message = "Title Cannot be Empty")
     private  String title;
     private  String description;
     @NotNull(message = "Status is required")
     private Status status;

     // Tracks when the task was initially created
     @CreatedDate
     private LocalDate createdDate;
     // Tracks when the task was initially updated
     @LastModifiedDate
     @Schema(description = "Date and time of task update")
     private LocalDate updatedDate;

}

