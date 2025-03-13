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
        Roсk
    }
    @NonNull
    private long id;
    @NonNull
    public String name_grope;
    private Genre genre;
    @NonNull
    public int time_performances;

    public Artist(long id, String name_grope, Genre genre, int time_performances) {
        this.id = id;
        this.name_grope = name_grope;
        this.genre = genre;
        this.time_performances = time_performances;
    }
}
