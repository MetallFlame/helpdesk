package com.helpdeskonboot.helpdesk.mapper;

import com.helpdeskonboot.helpdesk.dto.TicketDTO;
import com.helpdeskonboot.helpdesk.exception.TicketNotFoundException;
import com.helpdeskonboot.helpdesk.model.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TicketMapper {

    private ModelMapper mapper;

    public TicketMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Ticket toEntity(TicketDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Ticket.class);
    }

    public TicketDTO toDTO(Ticket entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TicketDTO.class);
    }

    public TicketDTO toDTOWithUsers(Ticket entity) {
        if (Objects.isNull(entity)) {
            throw new TicketNotFoundException();
        } else {
            TicketDTO dto = mapper.map(entity, TicketDTO.class);
            dto.setOwnerString(entity.getOwner().getFirstName() + " " + entity.getOwner().getLastName());
            if (entity.getAsignee() != null) {
                dto.setAsigneeString(entity.getAsignee().getFirstName() + " " + entity.getAsignee().getLastName());
            }
            if (entity.getApprover() != null) {
                dto.setAsigneeString(entity.getApprover().getFirstName() + " " + entity.getApprover().getLastName());
            }
            return dto;
        }

    }

    public List<TicketDTO> toDTOList(List<Ticket> tickets) {
        return tickets.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
