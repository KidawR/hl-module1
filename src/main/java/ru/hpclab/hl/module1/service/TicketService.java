package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.repository.TicketRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    public TicketEntity getTicketById(long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
    }


    public TicketEntity saveTicket(TicketEntity tickteEntity) {
        tickteEntity.setId(null);
        return ticketRepository.save(tickteEntity);
    }

    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }

    public TicketEntity updateTicket(long id, TicketEntity tickteEntity) {
        tickteEntity.setId(id);
        //when id is not empty save works with update logic
        return ticketRepository.save(tickteEntity);
    }

    public List<Map<String, Object>> getViewersCountBySector() {
        return ticketRepository.countViewersBySector().stream().map(row -> Map.of(
                "artistId", row[0],
                "artistName", row[1],
                "sector", row[2],
                "viewerCount", row[3]
        )).collect(Collectors.toList());
    }
}

