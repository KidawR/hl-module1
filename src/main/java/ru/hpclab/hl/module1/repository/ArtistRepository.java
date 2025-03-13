package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.model.Artist;

import java.util.*;

import static java.lang.String.format;

@Repository
public class ArtistRepository {
    public static final String ARTIST_NOT_FOUND_MSG = "HotelRoom with ID %s not found";
    public static final String ROOM_EXISTS_MSG = "HotelRoom with ID %s is already exists";

    private final Map<Long, Artist> artist = new HashMap<>();

    public List<Artist> findAll() {
        return new ArrayList<>(artist.values());
    }

    public Artist findById(long id) {
        final var room = artist.get(id);
        if (room == null) {
            throw new CustomException(format(ARTIST_NOT_FOUND_MSG, id));
        }
        return room;
    }

    public void delete(long id) {
        final var removed = artist.remove(id);
        if (removed == null) {
            throw new CustomException(format(ARTIST_NOT_FOUND_MSG, id));
        }
    }

    public Artist save(Artist room) {
        if (ObjectUtils.isEmpty(room.getId())) {
            room.setId(new Random().nextLong());
        }

        final var userData = artist.get(room.getId());
        if (userData != null) {
            throw new CustomException(format(ROOM_EXISTS_MSG, room.getId()));
        }

        artist.put(room.getId(), room);

        return room;
    }

    public Artist put(Artist room) {
        final var userData = artist.get(room.getId());
        if (userData == null) {
            throw new CustomException(format(ARTIST_NOT_FOUND_MSG, room.getId()));
        }

        final var removed = artist.remove(room.getId());
        if (removed != null) {
            artist.put(room.getId(), room);
        } else {
            throw new CustomException(format(ARTIST_NOT_FOUND_MSG, room.getId()));
        }

        return room;
    }

    public void clear(){
        artist.clear();
    }
}
