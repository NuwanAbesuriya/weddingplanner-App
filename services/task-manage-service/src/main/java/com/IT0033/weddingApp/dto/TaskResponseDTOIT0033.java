package com.IT0033.weddingApp.dto;

import com.IT0033.weddingApp.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTOIT0033 {
    private Long id;
    private Long eventId;
    private String description;
    private String assignedTo;
    private TaskStatus status;
    private LocalDate dueDate;
}
