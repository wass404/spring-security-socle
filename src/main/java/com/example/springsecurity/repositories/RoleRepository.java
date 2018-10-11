package com.example.springsecurity.repositories;

import com.example.springsecurity.Entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface RoleRepository extends JpaRepository<AppRole,Long>{
    public AppRole findByRole(String rolename);
}
