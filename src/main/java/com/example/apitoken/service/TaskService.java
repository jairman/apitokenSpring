package com.example.apitoken.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apitoken.dto.TaskRequest;
import com.example.apitoken.dto.TaskResponse;
import com.example.apitoken.entity.Task;
import com.example.apitoken.exception.ResourceNotFoundException;
import com.example.apitoken.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponse::new) // Convertir cada Task a TaskResponse
                .toList();
    }

    public Task save(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setCompleted(taskRequest.isCompleted());
        return taskRepository.save(task);
    }

    public Task delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
        return task;
    }

    public TaskResponse findByTitle(String title) {
        Task task = taskRepository.findByTitle(title).stream().findFirst().orElseThrow( () -> new ResourceNotFoundException("Task not found with title " + title));
        return new TaskResponse(task);
    }

    public List<TaskResponse> findByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed)
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public List<TaskResponse> findByTitleContaining(String word) {
        return taskRepository.findByTitleContaining(word)
                .stream()
                .map(TaskResponse::new)
                .toList();
    }

    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Task not found with id " + id));
        return new TaskResponse(task);
    }




}
