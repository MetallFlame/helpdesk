package com.helpdeskonboot.helpdesk.repository;



import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();

    Optional<User> getById(Long userId);

    Optional<User> getByEmail(String email);

    Optional<User> getByEmailAndPassword(String email, String password);

    List<User> getAllByRole(UserRole userRole);
}
