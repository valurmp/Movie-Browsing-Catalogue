package com.team18.MBC.core;

import java.util.List;

public interface UserService {
    User save(User user);

    void delete(User user);

    void registerUser(String username, String password);

    List<User> findAll();

    User findByUsername(String username);

    User login(User user);


}
