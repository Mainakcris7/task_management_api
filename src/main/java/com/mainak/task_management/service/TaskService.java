package com.mainak.task_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mainak.task_management.error.TaskException;
import com.mainak.task_management.model.Task;
import com.mainak.task_management.repository.TaskRepo;

@Service
public class TaskService {
    private TaskRepo repo;

    TaskService(TaskRepo repo) {
        this.repo = repo;
    }

    public ResponseEntity<List<Task>> getAllTasks(String sortBy, boolean ascending) {
        List<Task> tasks;
        if (sortBy == null) {
            tasks = repo.findAll();

        } else {
            if (ascending)
                tasks = repo.findAll(Sort.by(Sort.Direction.ASC, sortBy));
            else
                tasks = repo.findAll(Sort.by(Sort.Direction.DESC, sortBy));

        }
        if (tasks.isEmpty()) {
            throw new TaskException("No tasks found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public ResponseEntity<Task> getTaskById(int id) {
        Task task = repo.findById(id).orElse(null);
        if (task == null)
            throw new TaskException("No task found with id: " + id, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    public ResponseEntity<Task> createTask(Task task) {
        Task alreadyCreatedTask = repo.findByTitleIgnoreCase(task.getTitle());
        if (alreadyCreatedTask != null) {
            return ResponseEntity.status(HttpStatus.OK).body(alreadyCreatedTask);
        }
        task.setDone(false);
        task.setCreatedAt(LocalDateTime.now());
        Task createdTask = repo.save(task);

        if (createdTask == null)
            throw new TaskException("Failed to create a task with id: " + task.getId(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    public ResponseEntity<Task> completeTask(int id) {
        Task task = repo.findById(id).orElse(null);
        if (task == null) {
            throw new TaskException("No tasks found to complete with id: " + id, HttpStatus.BAD_REQUEST);
        }
        task.completeTask();
        Task completedTask = repo.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(completedTask);
    }

    public ResponseEntity<List<Task>> getAllDoneTasks() {
        List<Task> doneTasks = repo.findAllDoneTasks();
        if (doneTasks.isEmpty())
            throw new TaskException("No completed tasks found", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(doneTasks);
    }

    public ResponseEntity<List<Task>> getAllNotDoneTasks() {
        List<Task> notDoneTasks = repo.findAllNotDoneTasks();
        if (notDoneTasks.isEmpty())
            throw new TaskException("No incomplete tasks found", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.OK).body(notDoneTasks);
    }

    public ResponseEntity<Task> deleteTaskById(int id) {
        Task task = repo.findById(id).orElse(null);
        if (task == null)
            throw new TaskException("No tasks found for deletion with id: " + id, HttpStatus.BAD_REQUEST);
        repo.delete(task);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    public ResponseEntity<Task> updateTask(Task task) {
        Task updatedTask = repo.save(task);
        if (updatedTask == null)
            throw new TaskException("Failed to update task with id: " + task.getId(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }

    public ResponseEntity<List<Task>> getTasksByTitleContains(String title) {
        List<Task> tasks = repo.findByTitleContainingIgnoreCase(title);
        if (tasks.isEmpty()) {
            throw new TaskException("No tasks found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public ResponseEntity<List<Task>> deleteTasks(String which) {
        List<Task> tasks = null;
        if (which == null) {
            tasks = repo.findAll();
            if (tasks.isEmpty()) {
                throw new TaskException("No tasks found", HttpStatus.BAD_REQUEST);
            }
            repo.deleteAll();
        } else if (which.toLowerCase().equals("done") || which.toLowerCase().equals("not_done")) {
            if (which.toLowerCase().equals("done")) {
                tasks = repo.findAllDoneTasks();
            } else {
                tasks = repo.findAllNotDoneTasks();
            }
            tasks.forEach(task -> repo.delete(task));
        } else {
            throw new TaskException("'which' parameter contains invalid value: '" + which + "'",
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    public ResponseEntity<Task> undoTask(int id) {
        Task task = repo.findById(id).orElse(null);
        if (task == null) {
            throw new TaskException("Can't find task with id: " + id, HttpStatus.BAD_REQUEST);
        }
        task.setDone(false);
        task.setFinishedAt(null);
        Task undoneTask = repo.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(undoneTask);
    }

}
