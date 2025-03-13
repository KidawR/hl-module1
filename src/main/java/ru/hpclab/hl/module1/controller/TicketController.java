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

    @GetMapping("/getCount")
    public String getViewersOnSector() {
        return ticketService.getViewersOnSector();
    }

    @GetMapping("")
    public List<Ticket> getTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
    }

    @PostMapping("")
    public Ticket saveTicket(@RequestBody Ticket client) {
        return ticketService.saveTicket(client);
    }

    @PutMapping(value = "/{id}")
    public Ticket updateTicket(@PathVariable(required = false) String id, @RequestBody Ticket booking) {
        return ticketService.updateTicket(id, booking);
    }
}
