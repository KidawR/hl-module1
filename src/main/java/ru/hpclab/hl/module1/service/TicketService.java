package ru.hpclab.hl.module1.service;

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
        // Формируем строку с результатами
        StringBuilder result = new StringBuilder();
        for (Artist artist : artistRepository.findAll()) {
            List<Ticket> tickets = getAllTickets();
            Map<Ticket.Sector, Integer> sectorViewerCount = new HashMap<>();

            // Инициализируем счетчики для каждого сектора
            for (Ticket.Sector sector : Ticket.Sector.values()) {
                sectorViewerCount.put(sector, 0);
            }

            // Подсчитываем зрителей по секторам для данного артиста
            for (Ticket ticket : tickets) {
                if (ticket.getArtistId() == artist.getId()) {
                    Ticket.Sector sector = ticket.getSector();
                    sectorViewerCount.put(sector, sectorViewerCount.get(sector) + 1);
                }
            }
            result.append("Количество зрителей по секторам для артиста с ID ").append(artist.getId()).append(":\n");
            for (Map.Entry<Ticket.Sector, Integer> entry : sectorViewerCount.entrySet()) {
                result.append("Сектор ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" зрителей\n");
            }
        }

        return result.toString();
    }
}
