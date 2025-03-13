package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_Ticket")
@Getter
@Setter
public class TicketEntity {
    public enum Sector {
        A,
        B,
        C,
        D
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long artistId;
    private Long viewerId;
    private String date;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "viewer_id", nullable = false)
    private ViewerEntity viewerEntity;

    public TicketEntity() {
    }

    public TicketEntity(Long id, Long artistId, Long viewerId, String date, Sector sector) {
        this.id = id;
        this.artistId = artistId;
        this.viewerId = viewerId;
        this.date = date;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", artistId=" + artistId +
                ", viewerId=" + viewerId +
                ", date='" + date + '\'' +
                ", sector=" + sector +
                '}';
    }
}
