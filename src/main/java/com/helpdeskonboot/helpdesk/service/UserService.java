package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.model.User;

import java.util.List;

public interface UserService {
    List<UserDTO> getAll();

    UserDTO getDTOById(Long userId);

    UserDTO getDTOByEmail(String email);

    User getById(Long userId);

    User getByEmail(String email);

    UserDTO getUserFromDBWithData(UserDTO user);

    String getAllManagersEmails();

    String getAllEngineersEmails();
}
