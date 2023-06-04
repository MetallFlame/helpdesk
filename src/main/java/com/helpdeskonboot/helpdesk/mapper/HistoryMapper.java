package com.helpdeskonboot.helpdesk.mapper;


import com.helpdeskonboot.helpdesk.dto.HistoryDTO;
import com.helpdeskonboot.helpdesk.model.History;
import com.helpdeskonboot.helpdesk.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class HistoryMapper {

    private ModelMapper mapper;

    public HistoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public HistoryDTO toDTO(History entity) {
        if (Objects.isNull(entity)) {
            return null;
        } else {
            User historyUser = entity.getUser();
            entity.setUser(null);
            HistoryDTO dto = mapper.map(entity, HistoryDTO.class);
            dto.setUserName(historyUser.getFirstName() + " " + historyUser.getLastName());
            return dto;
        }
    }

    public List<HistoryDTO> toDTOList(List<History> histories) {
        return histories.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
