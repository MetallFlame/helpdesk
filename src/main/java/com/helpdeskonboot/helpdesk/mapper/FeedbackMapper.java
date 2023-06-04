package com.helpdeskonboot.helpdesk.mapper;

import com.helpdeskonboot.helpdesk.dto.FeedbackDTO;
import com.helpdeskonboot.helpdesk.model.Feedback;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeedbackMapper {

    private ModelMapper mapper;

    @Autowired
    public FeedbackMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Feedback toEntity(FeedbackDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Feedback.class);
    }

    public FeedbackDTO toDTO(Feedback entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, FeedbackDTO.class);
    }
}
