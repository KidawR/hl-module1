package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.entity.ArtistEntity;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.service.ArtistService;
import ru.hpclab.hl.module1.mapper.ArtistMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public List<Artist> getArtists() {
        return artistService.getAllArtists().stream()
                .map(ArtistMapper::toModel).collect(Collectors.toList());
    }

    @GetMapping("/artists/{id}")
    public Artist getArtistById(@PathVariable long id) {
        return ArtistMapper.toModel(artistService.getArtistById(id));
    }

    @DeleteMapping("/artists/{id}")
    public void deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
    }

    @PostMapping(value = "/artists")
    public Artist saveUser(@RequestBody Artist user) {
        return ArtistMapper.toModel(artistService.saveArtist(ArtistMapper.toEntity(user)));
    }

    @PutMapping(value = "/artists/{id}")
    public Artist updateArtist(@PathVariable(required = false) long id, @RequestBody Artist user) {
        return ArtistMapper.toModel(artistService.updateArtist(id, ArtistMapper.toEntity(user)));
    }
}
