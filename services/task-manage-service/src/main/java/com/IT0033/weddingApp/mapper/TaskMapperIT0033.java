package com.IT0033.weddingApp.mapper;


import com.IT0033.weddingApp.dto.TaskDTODIT0033;
import com.IT0033.weddingApp.dto.TaskResponseDTOIT0033;
import com.IT0033.weddingApp.entity.TaskIT0033;


public class TaskMapperIT0033 {

    /**
     * Converts a Task entity to a TaskResponseDTO.
     *
     * @param task the Task entity
     * @return the TaskResponseDTO
     */
    public static TaskResponseDTOIT0033 mapToTaskResponseDTO(TaskIT0033 task) {
        return new TaskResponseDTOIT0033(
                task.getId(),
                task.getEventId(),
                task.getDescription(),
                task.getAssignedTo(),
                task.getStatus(),
                task.getDueDate()
        );
    }

    /**
     * Converts a TaskDTO to a Task entity.
     *
     * @param taskDTO the TaskDTO
     * @return the Task entity
     */
    public static TaskIT0033 mapToTaskEntity(TaskDTODIT0033 taskDTO) {
        return new TaskIT0033(
                taskDTO.getId(),
                taskDTO.getEventId(),
                taskDTO.getDescription(),
                taskDTO.getAssignedTo(),
                taskDTO.getStatus(),
                taskDTO.getDueDate()
        );
    }

    /**
     * Converts a Task entity to a TaskDTO.
     *
     * @param task the Task entity
     * @return the TaskDTO
     */
    public static TaskDTODIT0033 mapToTaskDTO(TaskIT0033 task) {
        return new TaskDTODIT0033(
                task.getId(),
                task.getEventId(),
                task.getDescription(),
                task.getAssignedTo(),
                task.getStatus(),
                task.getDueDate()
        );
    }
}
