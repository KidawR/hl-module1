package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_ticket")
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
    private String date;
    @Enumerated(EnumType.STRING)
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "viewer_id", nullable = false)
    private ViewerEntity viewerEntity;
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artistEntity;
    public TicketEntity() {
    }

    public TicketEntity(Long id, ArtistEntity artistEntity, ViewerEntity viewerEntity, String date, Sector sector) {
        this.id = id;
        this.artistEntity = artistEntity;
        this.viewerEntity = viewerEntity;
        this.date = date;
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + id +
                ", artistId=" + artistEntity.getId()+
                ", viewerId=" + viewerEntity.getId() +
                ", date='" + date + '\'' +
                ", sector=" + sector +
                '}';
    }
}
