package com.example.apitoken.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apitoken.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getAll() {
        List<TaskResponse> tasks = taskService.findAll();

        ApiResponse<List<TaskResponse>> response = ApiResponse.success(tasks, HttpStatus.OK);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponse>> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task createdTask = taskService.save(taskRequest);
        TaskResponse taskResponse = new TaskResponse(createdTask);
        ApiResponse<TaskResponse> response = ApiResponse.success(taskResponse, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> delete(@PathVariable Long id) {
        Task deletedTask = taskService.delete(id);
        TaskResponse taskResponse = new TaskResponse(deletedTask);
        ApiResponse<TaskResponse> response = ApiResponse.success(taskResponse, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/title")
    public ResponseEntity<ApiResponse<TaskResponse>> getByTitle(@RequestParam String title) {
        TaskResponse taskResponse = taskService.findByTitle(title);
        ApiResponse<TaskResponse> response = ApiResponse.success(taskResponse, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{completed}")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> getByStatus(@PathVariable boolean completed) {
        List<TaskResponse> tasks = taskService.findByCompleted(completed);
        ApiResponse<List<TaskResponse>> response = ApiResponse.success(tasks, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TaskResponse>>> searchByWord(@RequestParam String word) {
        List<TaskResponse> tasks = taskService.findByTitleContaining(word);
        ApiResponse<List<TaskResponse>> response = ApiResponse.success(tasks, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponse>> getById(@PathVariable Long id) {
        TaskResponse task = taskService.findById(id);

        // Pasamos el objeto y el status 200 OK
        return ResponseEntity.ok(ApiResponse.success(task, HttpStatus.OK));
    }

}
