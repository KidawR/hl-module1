package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.model.Ticket;

import java.util.*;

import static java.lang.String.format;

@Repository
public class TicketRepository {
    public static final String TICKET_NOT_FOUND_MSG = "Ticket with ID %s not found";
    public static final String TICKET_EXISTS_MSG = "Ticket with ID %s is already exists";

    private final Map<Long, Ticket> tickets = new HashMap<>();

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    public Ticket findById(long id) {
        final var ticket = tickets.get(id);
        if (ticket == null) {
            throw new CustomException(format(TICKET_NOT_FOUND_MSG, id));
        }
        return ticket;
    }

    public void delete(long id) {
        final var removed = tickets.remove(id);
        if (removed == null) {
            throw new CustomException(format(TICKET_NOT_FOUND_MSG, id));
        }
    }

    public Ticket save(Ticket ticket) {
        if (ObjectUtils.isEmpty(ticket.getId())) {
            ticket.setId(new Random().nextLong());
        }

        final var userData = tickets.get(ticket.getId());
        if (userData != null) {
            throw new CustomException(format(TICKET_EXISTS_MSG, ticket.getId()));
        }

        tickets.put(ticket.getId(), ticket);

        return ticket;
    }

    public Ticket put(Ticket ticket) {
        final var userData = tickets.get(ticket.getId());
        if (userData == null) {
            throw new CustomException(format(TICKET_NOT_FOUND_MSG, ticket.getId()));
        }

        final var removed = tickets.remove(ticket.getId());
        if (removed != null) {
            tickets.put(ticket.getId(), ticket);
        } else {
            throw new CustomException(format(TICKET_NOT_FOUND_MSG, ticket.getId()));
        }

        return ticket;
    }

    public void clear(){
        tickets.clear();
    }
}
