package com.example.springsecurity.services;

import com.example.springsecurity.Entities.AppRole;
import com.example.springsecurity.Entities.AppUser;

public interface AccountService {
    public AppUser saveUser(AppUser user);

    public AppRole saveRole(AppRole role);

    public void AddRoleToUser(String username, String roleName);

    public AppUser findUserByUsername(String username);
}
