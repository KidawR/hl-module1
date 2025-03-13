package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.repository.TicketRepository;

import java.util.List;

public class TicketService {
    private final TicketRepository ticketRepository;

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
}
