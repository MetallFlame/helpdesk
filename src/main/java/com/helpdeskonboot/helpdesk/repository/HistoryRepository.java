package com.helpdeskonboot.helpdesk.repository;

import com.helpdeskonboot.helpdesk.model.History;

import java.util.List;

public interface HistoryRepository {
    List<History> getByTicketId(Long ticketId);

    void addHistory(History historyString);
}
