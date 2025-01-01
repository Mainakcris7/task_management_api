package com.mainak.task_management.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", unique = true)
    @NotBlank(message = "Task title should not be blank")
    @NotEmpty(message = "Task title should not be empty")
    private String title;

    @Column(name = "description")
    @NotBlank(message = "Task description should not be blank")
    @NotEmpty(message = "Task description should not be empty")
    private String description;

    @Column(name = "done")
    private boolean done;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "finished_at")
    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime finishedAt;

    public void completeTask() {
        this.setDone(true);
        this.setFinishedAt(LocalDateTime.now());
    }
}
