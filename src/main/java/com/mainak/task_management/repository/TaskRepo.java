package com.mainak.task_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mainak.task_management.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {
    Task findByTitleIgnoreCase(String title);

    List<Task> findByTitleContainingIgnoreCase(String pattern);

    @Query(value = "FROM Task t WHERE t.done IS true")
    List<Task> findAllDoneTasks();

    @Query(value = "FROM Task t WHERE t.done IS false")
    List<Task> findAllNotDoneTasks();
}
