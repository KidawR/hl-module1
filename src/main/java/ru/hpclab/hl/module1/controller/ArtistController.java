package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Artist;
import ru.hpclab.hl.module1.service.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService roomService) {
        this.artistService = roomService;
    }

    @GetMapping("")
    public List<Artist> getArtists() {
        return artistService.getAllArtist();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable String id) {
        return artistService.getArtistById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable String id) {
        artistService.deleteArtist(id);
    }

    @PostMapping("")
    public Artist saveArtist(@RequestBody Artist client) {
        return artistService.saveArtist(client);
    }

    @PutMapping(value = "/{id}")
    public Artist updateArtist(@PathVariable(required = false) String id, @RequestBody Artist room) {
        return artistService.updateArtist(id, room);
    }
}
