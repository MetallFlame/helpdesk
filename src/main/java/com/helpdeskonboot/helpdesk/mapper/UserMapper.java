package com.helpdeskonboot.helpdesk.mapper;

import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public User toEntity(UserDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDTO toDTO(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDTO.class);
    }

    public List<UserDTO> toDTOList(List<User> users) {
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }
}



