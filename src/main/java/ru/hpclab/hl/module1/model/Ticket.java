package ru.hpclab.hl.module1.model;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Ticket {
    public enum Sector {
        A,
        B,
        C,
        D
    }
    @NonNull
    private long id;
    @NonNull
    public long artist;
    @NonNull
    public long viewer;
    @NonNull
    public String date;
    public Sector sector;
    public Ticket(long id, long artist, long viewer, String date, Sector sector) {
        this.id = id;
        this.artist = artist;
        this.viewer = viewer;
        this.sector = sector;
        this.date = date;
    }
}
