package ru.hpclab.hl.module1.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hpclab.hl.module1.entity.ArtistEntity;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.entity.ViewerEntity;
import ru.hpclab.hl.module1.kafka.entity.EntityType;
import ru.hpclab.hl.module1.kafka.KafkaOperationMessage;
import ru.hpclab.hl.module1.kafka.entity.OperationType;
import ru.hpclab.hl.module1.mapper.ArtistMapper;
import ru.hpclab.hl.module1.mapper.TicketMapper;
import ru.hpclab.hl.module1.mapper.ViewerMapper;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.model.Ticket;
import ru.hpclab.hl.module1.model.Viewer;
import ru.hpclab.hl.module1.service.ArtistService;
import ru.hpclab.hl.module1.service.TicketService;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@Component
@RequiredArgsConstructor
public class KafkaMessageDispatcher {

    private final ArtistService artistService;
    private final TicketService ticketService;
    private final ViewerService viewerService;
    private final ObjectMapper objectMapper;
    //private final ObservabilityService observabilityService;

    public void dispatch(KafkaOperationMessage msg) {
        //String timerName = getClass().getSimpleName() + ":dispatch:" + msg.getEntity() + ":" + msg.getOperation();
        //observabilityService.start(timerName);

        EntityType entity = msg.getEntity();
        OperationType operation = msg.getOperation();

        switch (entity) {
            case ARTIST -> handleArtist(operation, msg.getPayload());
            case TICKET -> handleTicket(operation, msg.getPayload());
            case VIEWER -> handleViewer(operation, msg.getPayload());
        }

        //observabilityService.stop(timerName);
    }

    private void handleArtist(OperationType op, JsonNode payload) {
        //String timerName = getClass().getSimpleName() + ":handleArtist:" + op;
        //observabilityService.start(timerName);

        switch (op) {
            case POST -> {
                Artist artist = deserialize(payload, Artist.class);
                artistService.saveArtist(ArtistMapper.toEntity(artist));
            }
            case PUT -> {
                Artist artist = deserialize(payload, Artist.class);
                artistService.updateArtist(payload.get("id").asLong(), ArtistMapper.toEntity(artist));
            }
            case DELETE -> artistService.deleteArtist(payload.get("id").asLong());
            case CLEAR -> artistService.clearAllArtists();
        }

        //observabilityService.stop(timerName);
    }

    private void handleTicket(OperationType op, JsonNode payload) {
        //String timerName = getClass().getSimpleName() + ":handleTicket:" + op;
        //observabilityService.start(timerName);

        switch (op) {
            case POST -> {
                Ticket ticket = deserialize(payload, Ticket.class);
                ticketService.saveTicket(TicketMapper.toEntity(ticket));
            }
            case PUT -> {
                Ticket ticket = deserialize(payload, Ticket.class);
                ticketService.updateTicket(payload.get("id").asLong(), TicketMapper.toEntity(ticket));
            }
            case DELETE -> ticketService.deleteTicket(payload.get("id").asLong());
            case CLEAR -> ticketService.clearAllTickets();
        }

        //observabilityService.stop(timerName);
    }

    private void handleViewer(OperationType op, JsonNode payload) {
        //String timerName = getClass().getSimpleName() + ":handleViewer:" + op;
        //observabilityService.start(timerName);

        switch (op) {
            case POST -> {
                Viewer viewer = deserialize(payload, Viewer.class);
                viewerService.saveViewer(ViewerMapper.toEntity(viewer));
            }
            case PUT -> {
                Viewer viewer = deserialize(payload, Viewer.class);
                viewerService.updateViewer(payload.get("id").asLong(), ViewerMapper.toEntity(viewer));
            }
            case DELETE -> viewerService.deleteViewer(payload.get("id").asLong());
            case CLEAR -> viewerService.clearAllViewers();
        }

        //observabilityService.stop(timerName);
    }

    private <T> T deserialize(JsonNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize payload to " + clazz.getSimpleName(), e);
        }
    }
}
