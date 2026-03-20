package com.example.apitoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apitoken.entity.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTitle(String title);

    List<Task> findByCompleted(boolean completed);

    List<Task> findByTitleContaining(String word); // Buscar tareas por título que contenga una palabra clave

}
