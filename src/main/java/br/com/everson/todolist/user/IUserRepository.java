package br.com.everson.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<UserModel,UUID>{
    // Add methods for CRUD operations here
    UserModel findByUserName(String userName);
}
