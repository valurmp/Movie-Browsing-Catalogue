package com.team18.MBC.Repositories;

import com.team18.MBC.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    void delete(User user);

    List<User> findAll();

    Optional<User> findByUsername(String username);

}
