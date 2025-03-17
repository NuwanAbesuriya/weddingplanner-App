package com.IT0033.weddingApp.dto;



import com.IT0033.weddingApp.entity.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDTODIT0033 {
    private Long id;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    @NotBlank(message = "Description is required")
    private String description;

    private String assignedTo;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @FutureOrPresent(message = "Due date cannot be in the past")
    private LocalDate dueDate;
}
