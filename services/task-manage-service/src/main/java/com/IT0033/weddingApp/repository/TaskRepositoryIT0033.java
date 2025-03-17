package com.IT0033.weddingApp.repository;

import com.IT0033.weddingApp.entity.TaskIT0033;
import com.IT0033.weddingApp.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositoryIT0033 extends JpaRepository<TaskIT0033,Long> {
    List<TaskIT0033> findByStatus(TaskStatus status);
    List<TaskIT0033> findByEventId(Long eventId);
}
