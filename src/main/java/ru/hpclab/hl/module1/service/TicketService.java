package ru.hpclab.hl.module1.service;


import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.repository.TicketRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

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

    public Map<Long, Map<TicketEntity.Sector, Integer>> getViewersOnSector() {
        Map<Long, Map<TicketEntity.Sector, Integer>> allArtistsViewerCount = new HashMap<>();

        for (TicketEntity ticket : getAllTickets()) {
            long artistId = ticket.getArtistId();
            TicketEntity.Sector sector = ticket.getSector();

            allArtistsViewerCount
                    .computeIfAbsent(artistId, k -> new HashMap<>())
                    .merge(sector, 1, Integer::sum);
        }
        return allArtistsViewerCount;

    }
}

