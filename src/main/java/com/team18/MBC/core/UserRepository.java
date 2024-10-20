package com.team18.MBC.core;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    void delete(User user);

    List<User> findAll();

    Optional<User> findByUsername(String username);
    

}
