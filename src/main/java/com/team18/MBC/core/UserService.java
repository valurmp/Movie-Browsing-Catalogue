package com.team18.MBC.core;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    User save(User user);
    void delete(User user);

    void registerUser(String username, String password);

    List<User> findAll();
    User findByUsername(String username);
    User login(User user);
    User findUserById(Long id);
    void updatePassword(User user, String newPassword);
}
