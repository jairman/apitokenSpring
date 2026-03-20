package com.example.apitoken.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apitoken.entity.Task;
import com.example.apitoken.repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
        return task;
    }

    public Task findByTitle(String title) {
        return taskRepository.findByTitle(title).stream().findFirst().orElse(null);
    }

    public List<Task> findByCompleted(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public List<Task> findByTitleContaining(String word) {
        return taskRepository.findByTitleContaining(word);
    }




}
