package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("")
    public List<Ticket> getBookings() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getBookingById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable String id) {
        ticketService.deleteTicket(id);
    }

    @PostMapping("")
    public Ticket saveBooking(@RequestBody Ticket client) {
        return ticketService.saveTicket(client);
    }

    @PutMapping(value = "/{id}")
    public Ticket updateBooking(@PathVariable(required = false) String id, @RequestBody Ticket booking) {
        return ticketService.updateTicket(id, booking);
    }
}
