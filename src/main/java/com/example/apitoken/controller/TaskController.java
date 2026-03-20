package com.example.apitoken.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apitoken.dto.TaskRequest;
import com.example.apitoken.dto.TaskResponse;
import com.example.apitoken.entity.Task;
import com.example.apitoken.service.TaskService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponse> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task createdTask = taskService.save(taskRequest);
        return ResponseEntity.ok(createdTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Task deletedTask = taskService.delete(id);
        return ResponseEntity.ok(deletedTask);
    }

    @GetMapping("/search/title")
    public ResponseEntity<TaskResponse> getByTitle(@RequestParam String title) {
        TaskResponse taskResponse = taskService.findByTitle(title);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/status/{completed}")
    public List<TaskResponse> getByStatus(@PathVariable boolean completed) {
        return taskService.findByCompleted(completed);
    }

    @GetMapping("/search")
    public List<TaskResponse> searchByWord(@RequestParam String word) {
        return taskService.findByTitleContaining(word);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
     return ResponseEntity.ok(taskService.findById(id));
    }

}
