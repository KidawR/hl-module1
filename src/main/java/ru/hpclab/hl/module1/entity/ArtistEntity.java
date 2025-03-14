package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_Artist")
@Getter
@Setter
public class ArtistEntity {

    public enum Genre {
        METAL,
        K_POP,
        ROCK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameGroup; // Исправлено с name_grope

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private int timePerformances; // Исправлено с time_performances

    public ArtistEntity() {}

    public ArtistEntity(Long id, String nameGroup, Genre genre, int timePerformances) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.genre = genre;
        this.timePerformances = timePerformances;
    }

    @Override
    public String toString() {
        return "ArtistEntity{" +
                "id=" + id +
                ", nameGroup='" + nameGroup + '\'' +
                ", genre=" + genre +
                ", timePerformances=" + timePerformances +
                '}';
    }
}
