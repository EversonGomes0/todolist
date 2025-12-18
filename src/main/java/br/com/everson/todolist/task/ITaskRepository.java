package br.com.everson.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    // Add methods for CRUD operations here
    List<TaskModel> findByIdUser(UUID idUser);
}

