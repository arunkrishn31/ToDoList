package com.project.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.todolist.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{

    void deleteById(int id);

    Optional<User> findByName(String username);
}
