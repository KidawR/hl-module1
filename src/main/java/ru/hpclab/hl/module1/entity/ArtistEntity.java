package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hpclab.hl.module1.model.Artist;

@Entity
@Table(name = "t_Artist")
@Getter
@Setter
public class ArtistEntity {

    public ArtistEntity() {

    }

    public enum Genre {
        Metal,
        K_POP,
        Rock
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name_grope;

    @Setter
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int time_performances;

    public ArtistEntity(Long id, String name_grope, Genre genre, int time_performances) {
        this.id = id;
        this.name_grope = name_grope;
        this.genre = genre;
        this.time_performances = time_performances;
    }

    @Override
    public String toString() {
        return "ArtistEntity{" +
                "id=" + id +
                ", name_grope='" + name_grope + '\'' +
                ", genre=" + genre +
                ", time_performances=" + time_performances +
                '}';
    }
}
