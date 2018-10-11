package com.example.springsecurity.repositories;

import com.example.springsecurity.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource
public interface TaskRepository extends JpaRepository<Task, Long> {
}
