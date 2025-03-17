package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.dto.TaskDTODIT0033;
import com.IT0033.weddingApp.dto.TaskResponseDTOIT0033;
import com.IT0033.weddingApp.entity.TaskIT0033;
import com.IT0033.weddingApp.entity.TaskStatus;
import com.IT0033.weddingApp.exception.ResourceNotFoundException;
import com.IT0033.weddingApp.repository.TaskRepositoryIT0033;
import com.IT0033.weddingApp.mapper.TaskMapperIT0033;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceIT0033 {

    private final TaskRepositoryIT0033 taskRepository;


    /**
     * Creates a new task.
     *
     * @param taskDTO Task DTO containing task details.
     * @return Response DTO of the created task.
     */
    public TaskResponseDTOIT0033 createTask(TaskDTODIT0033 taskDTO) {
        TaskIT0033 taskEntity = TaskMapperIT0033.mapToTaskEntity(taskDTO);
        TaskIT0033 savedTask = taskRepository.save(taskEntity);
        return TaskMapperIT0033.mapToTaskResponseDTO(savedTask);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id Task ID.
     * @return Response DTO of the retrieved task.
     */
    public TaskResponseDTOIT0033 getTaskById(Long id) {
        TaskIT0033 task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));
        return TaskMapperIT0033.mapToTaskResponseDTO(task);
    }

    /**
     * Updates an existing task.
     *
     * @param id      Task ID.
     * @param taskDTO Task DTO with updated details.
     * @return Response DTO of the updated task.
     */
    public TaskResponseDTOIT0033 updateTask(Long id, TaskDTODIT0033 taskDTO) {
        TaskIT0033 existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with ID: " + id));

        // Update the entity fields
        existingTask.setEventId(taskDTO.getEventId());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setAssignedTo(taskDTO.getAssignedTo());
        existingTask.setStatus(taskDTO.getStatus());
        existingTask.setDueDate(taskDTO.getDueDate());

        TaskIT0033 updatedTask = taskRepository.save(existingTask);
        return TaskMapperIT0033.mapToTaskResponseDTO(updatedTask);
    }

    /**
     * Deletes a task by its ID.
     *
     * @param id Task ID.
     */
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with ID: " + id);
        }
        taskRepository.deleteById(id);
    }

    /**
     * Retrieves tasks by their status.
     *
     * @param status Task status.
     * @return List of response DTOs of the tasks with the specified status.
     */
    public List<TaskResponseDTOIT0033> getTasksByStatus(TaskStatus status) {
        List<TaskIT0033> tasks = taskRepository.findByStatus(status);
        return tasks.stream()
                .map(TaskMapperIT0033::mapToTaskResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves tasks by their associated event ID.
     *
     * @param eventId Event ID.
     * @return List of response DTOs of the tasks associated with the specified event ID.
     */
    public List<TaskResponseDTOIT0033> getTasksByEventId(Long eventId) {
        List<TaskIT0033> tasks = taskRepository.findByEventId(eventId);
        return tasks.stream()
                .map(TaskMapperIT0033::mapToTaskResponseDTO)
                .collect(Collectors.toList());
    }
}
