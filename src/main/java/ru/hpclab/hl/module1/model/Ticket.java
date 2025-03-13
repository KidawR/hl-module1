package ru.hpclab.hl.module1.model;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

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
    public long artistId;
    @NonNull
    public long viewerId;
    @NonNull
    public String date;
    public Sector sector;
    public Ticket(long id, long artistId, long viewerId, String date, Sector sector) {
        this.id = id;
        this.artistId = artistId;
        this.viewerId = viewerId;
        this.sector = sector;
        this.date = date;
    }
}
