package ru.hpclab.hl.module1.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.util.Date;

@Getter
@Setter
public class Viewer {
    @NonNull
    private long id;
    @NonNull
    public String name;
    @NonNull
    public String email;

    public Viewer(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
