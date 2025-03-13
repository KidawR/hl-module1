package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.repository.ArtistRepository;

import java.util.List;

public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(String id) {
        return artistRepository.findById(Long.getLong(id));
    }

    public Artist saveArtist(Artist user) {
        return artistRepository.save(user);
    }

    public void deleteArtist(String id) {
        artistRepository.delete(Long.getLong(id));
    }

    public Artist updateArtist(String id, Artist room) {
        room.setId(Long.getLong(id));
        return artistRepository.put(room);
    }
}
