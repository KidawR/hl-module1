package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.mapper.SectorStats;
import ru.hpclab.hl.module1.mapper.TicketMapper;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.service.TicketService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ObservabilityService observabilityService;
    @Autowired
    public TicketController(TicketService artistService, ObservabilityService observabilityService) {
        this.ticketService = artistService;
        this.observabilityService = observabilityService;
    }

    @GetMapping("")
    public List<Ticket> getArtists() {
        this.observabilityService.start(getClass().getSimpleName() + ":getArtists");

        List<Ticket> temp = ticketService.getAllTickets().stream()
                .map(TicketMapper::toModel).collect(Collectors.toList());
        this.observabilityService.stop(getClass().getSimpleName() + ":getArtists");
        return temp;
    }
    @DeleteMapping("/clear")
    public void clearTickets() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearTickets");
        ticketService.clearAllTickets(); // Метод для очистки всех билетов
        this.observabilityService.stop(getClass().getSimpleName() + ":clearTickets");
    }
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getTicketById");

        Ticket temp = TicketMapper.toModel(ticketService.getTicketById(id));
        this.observabilityService.stop(getClass().getSimpleName() + ":getTicketById");
        return temp;
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteTicket");
        ticketService.deleteTicket(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteTicket");
    }

    @PostMapping(value = "")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveTicket");
        Ticket temp = TicketMapper.toModel(ticketService.saveTicket(TicketMapper.toEntity(ticket)));
        this.observabilityService.stop(getClass().getSimpleName() + ":saveTicket");
        return temp;
    }

    @PutMapping(value = "/{id}")
    public Ticket updateTicket(@PathVariable(required = false) long id, @RequestBody Ticket user) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateTicket");
        Ticket temp = TicketMapper.toModel(ticketService.updateTicket(id, TicketMapper.toEntity(user)));
        this.observabilityService.stop(getClass().getSimpleName() + ":updateTicket");
        return temp;
    }
}
