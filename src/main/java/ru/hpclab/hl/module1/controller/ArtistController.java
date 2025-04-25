package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.service.ArtistService;
import ru.hpclab.hl.module1.mapper.ArtistMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@RestController
@RequestMapping("/artists")
//@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;
    private final ObservabilityService observabilityService;
    @Autowired
    public ArtistController(ArtistService artistService, ObservabilityService observabilityService) {
        this.artistService = artistService;
        this.observabilityService = observabilityService;
    }

    @GetMapping
    public List<Artist> getArtists() {
        this.observabilityService.start(getClass().getSimpleName() + ":create");

        List<Artist> temp = artistService.getAllArtists().stream()
                .map(ArtistMapper::toModel).collect(Collectors.toList());

        this.observabilityService.stop(getClass().getSimpleName() + ":create");
        return temp;
    }
    @DeleteMapping("/clear")
    public void clearArtists() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearArtists");

        artistService.clearAllArtists(); // Метод для очистки всех артистов

        this.observabilityService.stop(getClass().getSimpleName() + ":clearArtists");
    }
    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getArtistById");

        Artist temp = ArtistMapper.toModel(artistService.getArtistById(id));

        this.observabilityService.stop(getClass().getSimpleName() + ":getArtistById");
        return temp;
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteArtist");
        artistService.deleteArtist(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteArtist");
    }

    @PostMapping
    public Artist saveUser(@RequestBody Artist user) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveUser");
        Artist temp = ArtistMapper.toModel(artistService.saveArtist(ArtistMapper.toEntity(user)));
        this.observabilityService.stop(getClass().getSimpleName() + ":saveUser");
        return temp;
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable long id, @RequestBody Artist user) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateArtist");
        Artist temp = ArtistMapper.toModel(artistService.updateArtist(id, ArtistMapper.toEntity(user)));
        this.observabilityService.stop(getClass().getSimpleName() + ":updateArtist");
        return temp;
    }

}
