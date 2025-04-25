package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.ArtistEntity;

import ru.hpclab.hl.module1.mapper.ArtistMapper;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.repository.ArtistRepository;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import static java.lang.String.format;
@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";
    private final ObservabilityService observabilityService;
    public ArtistService(ArtistRepository viewerRepository, ObservabilityService observabilityService) {
        this.artistRepository = viewerRepository;
        this.observabilityService = observabilityService;
    }

    public void clearAllArtists() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllArtists");

        artistRepository.deleteAll(); // Удаляет всех артистов
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllArtists");
    }

    public List<ArtistEntity> getAllArtists() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllArtists");

        List<ArtistEntity> temp = artistRepository.findAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllArtists");
        return temp;
    }

    public ArtistEntity getArtistById(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getArtistById");

        ArtistEntity temp = artistRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
        this.observabilityService.stop(getClass().getSimpleName() + ":getArtistById");
        return temp;
    }

    public ArtistEntity saveArtist(ArtistEntity viewerEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveArtist");

        ArtistEntity temp = artistRepository.save(viewerEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":saveArtist");
        return temp;
    }


    public void deleteArtist(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteArtist");

        artistRepository.deleteById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteArtist");
    }

    public ArtistEntity updateArtist(long id, ArtistEntity viewerEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateArtist");

        viewerEntity.setId(id);
        //when id is not empty save works with update logic
        ArtistEntity temp = artistRepository.save(viewerEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateArtist");
        return temp;
    }
}
