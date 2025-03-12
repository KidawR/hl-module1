package ru.hpclab.hl.module1.model;

import io.micrometer.common.lang.NonNull;

import java.time.LocalDate;

public class Ticket {
    @NonNull
    public Artist artist;
    @NonNull
    public Viewer viewer;
    @NonNull
    public LocalDate date;
}
