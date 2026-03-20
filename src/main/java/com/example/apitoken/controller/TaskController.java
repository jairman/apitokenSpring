package com.example.apitoken.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apitoken.entity.Task;
import com.example.apitoken.service.TaskService;

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
    public List<Task> getAll() {
        return taskService.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        Task deletedTask = taskService.delete(id);
        return ResponseEntity.ok(deletedTask);
    }

    @GetMapping("/search/title")
    public ResponseEntity<Task> getByTitle(@RequestParam String title) {
        Task task = taskService.findByTitle(title);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{completed}")
    public List<Task> getByStatus(@PathVariable boolean completed) {
        return taskService.findByCompleted(completed);
    }

    @GetMapping("/search")
    public List<Task> searchByWord(@RequestParam String word) {
        return taskService.findByTitleContaining(word);
    }

}
