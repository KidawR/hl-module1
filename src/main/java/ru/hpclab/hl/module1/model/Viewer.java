package ru.hpclab.hl.module1.model;

import io.micrometer.common.lang.NonNull;

public class Viewer {
    @NonNull
    private long id;
    @NonNull
    public String name;
    @NonNull
    public String email;
}
