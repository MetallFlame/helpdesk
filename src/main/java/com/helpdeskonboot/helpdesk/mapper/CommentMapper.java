package com.helpdeskonboot.helpdesk.mapper;


import com.helpdeskonboot.helpdesk.dto.CommentDTO;
import com.helpdeskonboot.helpdesk.exception.UserNotFoundException;
import com.helpdeskonboot.helpdesk.model.Comment;
import com.helpdeskonboot.helpdesk.model.User;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    private ModelMapper mapper;
    private UserService userService;

    @Autowired
    public CommentMapper(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    public CommentDTO toDTO(Comment entity) throws UserNotFoundException {
        if (Objects.isNull(entity)) {
            return null;
        } else {
            User commentator = userService.getById(entity.getUser().getId());
            CommentDTO dto = mapper.map(entity, CommentDTO.class);
            dto.setUserName(commentator.getFirstName() + " " + commentator.getLastName());
            return dto;
        }
    }

    public List<CommentDTO> toDTOList(List<Comment> comments) throws UserNotFoundException {
        return comments.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
