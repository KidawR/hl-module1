package ru.hpclab.hl.module1.mapper;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ru.hpclab.hl.module1.entity.ArtistEntity;
import ru.hpclab.hl.module1.entity.TicketEntity;
import ru.hpclab.hl.module1.entity.ViewerEntity;
import ru.hpclab.hl.module1.model.Ticket;

public class TicketMapper {
    private TicketMapper() {
    }

    public static TicketEntity toEntity(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        return new TicketEntity(
                null,  // ID автоматически генерируется
                new ArtistEntity(ticket.getArtistId()),
                new ViewerEntity(ticket.getViewerId()),
                ticket.getDate(),
                TicketEntity.Sector.valueOf(ticket.getSector().name())
        );
    }

    public static Ticket toModel(TicketEntity ticketEntity) {
        if (ticketEntity == null) {
            return null;
        }
        return new Ticket(
                ticketEntity.getId(),
                ticketEntity.getArtistEntity().getId(),
                ticketEntity.getViewerEntity().getId(), // Получаем viewerId из объекта
                ticketEntity.getDate(),
                Ticket.Sector.valueOf(ticketEntity.getSector().name())
        );
    }
}
