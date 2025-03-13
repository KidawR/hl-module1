package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.repository.ArtistRepository;

import java.util.List;

public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository roomRepository) {
        this.artistRepository = roomRepository;
    }

    public List<Artist> getAllHotelRooms() {
        return artistRepository.findAll();
    }

    public Artist getHotelRoomById(String id) {
        return artistRepository.findById(Long.getLong(id));
    }

    public Artist saveHotelRoom(Artist user) {
        return artistRepository.save(user);
    }

    public void deleteHotelRoom(String id) {
        artistRepository.delete(Long.getLong(id));
    }

    public Artist updateHotelRoom(String id, Artist room) {
        room.setId(Long.getLong(id));
        return artistRepository.put(room);
    }
}
