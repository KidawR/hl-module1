package ru.hpclab.hl.module1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.repository.ArtistRepository;
import ru.hpclab.hl.module1.repository.TicketRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {
    private final TicketRepository ticketRepository;
    private ArtistRepository artistRepository = new ArtistRepository();

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(String id) {
        return ticketRepository.findById(Long.getLong(id));
    }

    public Ticket saveTicket(Ticket user) {
        return ticketRepository.save(user);
    }

    public void deleteTicket(String id) {
        ticketRepository.delete(Long.getLong(id));
    }

    public Ticket updateTicket(String id, Ticket ticket) {
        ticket.setId(Long.getLong(id));
        return ticketRepository.put(ticket);
    }
    public String getViewersOnSector() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Long, Map<Ticket.Sector, Integer>> allArtistsViewerCount = new HashMap<>();

        //Подсчитываем зрителей по секторам для каждого артиста
        for (Ticket ticket : getAllTickets()) {
            long artistId = ticket.getArtistId();
            Ticket.Sector sector = ticket.getSector();

            //Инициализируем счетчик для артиста, если его еще нет
            allArtistsViewerCount.putIfAbsent(artistId, new HashMap<>());
            Map<Ticket.Sector, Integer> sectorCount = allArtistsViewerCount.get(artistId);

            //Инициализируем счетчик для сектора, если его еще нет
            sectorCount.putIfAbsent(sector, 0);
            sectorCount.put(sector, sectorCount.get(sector) + 1);
        }

        // JSON-объект
        try {
            return objectMapper.writeValueAsString(allArtistsViewerCount);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // в случае ошибки
        }
    }
}
