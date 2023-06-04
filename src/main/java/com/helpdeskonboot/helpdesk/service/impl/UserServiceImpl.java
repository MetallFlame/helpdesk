package com.helpdeskonboot.helpdesk.service.impl;


import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.exception.UserNotFoundException;
import com.helpdeskonboot.helpdesk.mapper.UserMapper;
import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.model.UserRole;
import com.helpdeskonboot.helpdesk.repository.UserRepository;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAll() {
        return userMapper.toDTOList(userRepository.getAll());
    }

    @Override
    public UserDTO getDTOById(Long userId) {
        return userMapper.toDTO(getById(userId));
    }

    @Override
    public UserDTO getDTOByEmail(String email) {
        return userMapper.toDTO(getByEmail(email));
    }

    @Override
    public User getById(Long userId) {
        return userRepository.getById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDTO getUserFromDBWithData(UserDTO userDTO) {
        Optional<User> userWithData = userRepository.getByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        return userWithData.map(userMapper::toDTO).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public String getAllManagersEmails() {
        return getAllEmailsByRole(UserRole.MANAGER);
    }

    @Override
    public String getAllEngineersEmails() {
        return getAllEmailsByRole(UserRole.ENGINEER);
    }

    private String getAllEmailsByRole(UserRole role) {
        StringBuilder emails = new StringBuilder();
        userRepository.getAllByRole(role).forEach(user -> emails.append(user.getEmail()).append(", "));
        return (emails.length() != 0) ? emails.substring(0, emails.length() - 2) : emails.toString();
    }


}
