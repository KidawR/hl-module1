package ru.hpclab.hl.module1.model;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artist {
    public enum Genre {
        Metal,
        K_POP,
        Rock
    }
    @NonNull
    private long id;
    @NonNull
    public String nameGroup;
    private Genre genre;
    @NonNull
    public int time_performances;

    public Artist(long id, String nameGroup, Genre genre, int time_performances) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.genre = genre;
        this.time_performances = time_performances;
    }
    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name_grope='" + nameGroup + '\'' +
                ", genre=" + genre +
                ", time_performances=" + time_performances +
                '}';
    }
}
