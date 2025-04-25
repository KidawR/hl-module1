package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.repository.TicketRepository;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;
@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";
    private final ObservabilityService observabilityService;
    public TicketService(TicketRepository ticketRepository, ObservabilityService observabilityService) {
        this.ticketRepository = ticketRepository;
        this.observabilityService = observabilityService;
    }

    public List<TicketEntity> getAllTickets() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        List<TicketEntity> temp = ticketRepository.findAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
        return temp;
    }

    public TicketEntity getTicketById(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        TicketEntity temp = ticketRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
        return temp;
    }


    public TicketEntity saveTicket(TicketEntity tickteEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        tickteEntity.setId(null);
        TicketEntity temp = ticketRepository.save(tickteEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
        return temp;
    }

    public void deleteTicket(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        ticketRepository.deleteById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
    }

    public TicketEntity updateTicket(long id, TicketEntity tickteEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        tickteEntity.setId(id);
        //when id is not empty save works with update logic
        TicketEntity temp = ticketRepository.save(tickteEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
        return temp;
    }

    public List<Map<String, Object>> getViewersCountBySector() {
        this.observabilityService.start(getClass().getSimpleName() + ":getViewersCountBySector");
        List<Map<String, Object>> temp = ticketRepository.countViewersBySector().stream()
                .collect(Collectors.groupingBy(
                        row -> Map.of(
                                "artistId", row[0],
                                "artistName", row[1]
                        ),
                        Collectors.mapping(row -> Map.of(
                                "sector", row[2],
                                "viewerCount", row[3]
                        ), Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> {
                    Map<String, Object> artistData = new HashMap<>(entry.getKey());
                    artistData.put("sectors", entry.getValue());
                    return artistData;
                })
                .collect(Collectors.toList());
        this.observabilityService.stop(getClass().getSimpleName() + ":getViewersCountBySector");
        return temp;
    }

    public void clearAllTickets() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");
        ticketRepository.deleteAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
    }
}

