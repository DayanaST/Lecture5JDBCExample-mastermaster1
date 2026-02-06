package com.company.controllers.interfaces;

import com.company.models.User;

import java.util.List;

public interface IUserController {
    String createUser(String name);
    String getUser(int id);
    List<User> getAllUsers();
}
