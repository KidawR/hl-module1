package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.ArtistEntity;

import ru.hpclab.hl.module1.mapper.ArtistMapper;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.repository.ArtistRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";

    public ArtistService(ArtistRepository viewerRepository) {
        this.artistRepository = viewerRepository;
    }

    public void clearAllArtists() {
        artistRepository.deleteAll(); // Удаляет всех артистов
    }

    public List<ArtistEntity> getAllArtists() {
        return artistRepository.findAll();
    }

    public ArtistEntity getArtistById(long id) {
        return artistRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
    }

    public ArtistEntity saveArtist(ArtistEntity viewerEntity) {
        return artistRepository.save(viewerEntity);
    }


    public void deleteArtist(long id) {
        artistRepository.deleteById(id);
    }

    public ArtistEntity updateArtist(long id, ArtistEntity viewerEntity) {
        viewerEntity.setId(id);
        //when id is not empty save works with update logic
        return artistRepository.save(viewerEntity);
    }
}
