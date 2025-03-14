package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.mapper.TicketMapper;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.service.TicketService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService artistService) {
        this.ticketService = artistService;
    }

    @GetMapping("/ticket")
    public List<Ticket> getArtists() {
        return ticketService.getAllTickets().stream()
                .map(TicketMapper::toModel).collect(Collectors.toList());
    }

    @GetMapping("/ticket/{id}")
    public Ticket getTicketById(@PathVariable long id) {
        return TicketMapper.toModel(ticketService.getTicketById(id));
    }

    @DeleteMapping("/ticket/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.deleteTicket(id);
    }

    @PostMapping(value = "/ticket")
    public Ticket saveUser(@RequestBody Ticket user) {
        return TicketMapper.toModel(ticketService.saveTicket(TicketMapper.toEntity(user)));
    }

    @PutMapping(value = "/ticket/{id}")
    public Ticket updateTicket(@PathVariable(required = false) long id, @RequestBody Ticket user) {
        return TicketMapper.toModel(ticketService.updateTicket(id, TicketMapper.toEntity(user)));
    }
    @GetMapping("/viewers-on-sector")
    public ResponseEntity<Map<Long, Map<TicketEntity.Sector, Integer>>> getViewersOnSector() {
        return ResponseEntity.ok(ticketService.getViewersOnSector());
    }
}
