package ru.hpclab.hl.module1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist {
    public enum Genre {
        METAL,
        K_POP,
        ROCK
    }

    private long id;
    public String nameGroup;
    private Genre genre;
    public int timePerformances;

    public Artist(long id, String nameGroup, Genre genre, int timePerformances) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.genre = genre;
        this.timePerformances = timePerformances;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name_grope='" + nameGroup + '\'' +
                ", genre=" + genre +
                ", time_performances=" + timePerformances +
                '}';
    }
}
