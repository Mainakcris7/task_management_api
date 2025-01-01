package com.mainak.task_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mainak.task_management.model.Task;
import com.mainak.task_management.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService service;

    TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam(required = false) String sortBy,
            @RequestParam(required = false) boolean ascending) {
        return service.getAllTasks(sortBy, ascending);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return service.getTaskById(id);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Task>> getTasksByTitleContains(@PathVariable String title) {
        return service.getTasksByTitleContains(title);
    }

    @GetMapping("/done")
    public ResponseEntity<List<Task>> getAllDoneTasks() {
        return service.getAllDoneTasks();
    }

    @GetMapping("/not_done")
    public ResponseEntity<List<Task>> getAllNotDoneTasks() {
        return service.getAllNotDoneTasks();
    }

    @PostMapping("")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return service.createTask(task);
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<Task> updateTaskCompletion(@PathVariable int id) {
        return service.completeTask(id);
    }

    @PutMapping("/undo/{id}")
    public ResponseEntity<Task> updateTaskNotCompletion(@PathVariable int id) {
        return service.undoTask(id);
    }

    @PutMapping("")
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task) {
        return service.updateTask(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable int id) {
        return service.deleteTaskById(id);
    }

    @DeleteMapping("/all")
    public ResponseEntity<List<Task>> deleteTasks(@RequestParam(required = false) String which) {
        return service.deleteTasks(which);
    }

}
