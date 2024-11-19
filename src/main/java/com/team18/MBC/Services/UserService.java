package com.team18.MBC.Services;

import com.team18.MBC.core.Image;
import com.team18.MBC.core.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    void delete(User user);

    void registerUser(String username, String password);

    List<User> findAll();
    User findByUsername(String username);
    User login(User user);
    User findUserById(Long id);
    void updatePassword(User user, String newPassword);

    Optional<Image> getProfileImageForUser(Long userId);
}
