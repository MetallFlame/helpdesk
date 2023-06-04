package com.helpdeskonboot.helpdesk.mapper;


import com.helpdeskonboot.helpdesk.dto.AttachmentDTO;
import com.helpdeskonboot.helpdesk.model.Attachment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AttachmentMapper {

    private ModelMapper mapper;

    @Autowired
    public AttachmentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public AttachmentDTO toDTO(Attachment entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AttachmentDTO.class);
    }

    public List<AttachmentDTO> toDTOList(List<Attachment> attachments) {
        return attachments.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
