package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.entity.ArtistEntity;
import ru.hpclab.hl.module1.model.Artist;

public class ArtistMapper {
    private ArtistMapper() {
    }

    public static ArtistEntity toEntity(Artist artist) {
        if (artist == null) {
            return null;
        }
        return new ArtistEntity(
                null,  // ID автоматически генерируется в базе
                artist.getNameGroup(),
                ArtistEntity.Genre.valueOf(artist.getGenre().name()),
                artist.getTimePerformances()
        );
    }

    public static Artist toModel(ArtistEntity artistEntity) {
        if (artistEntity == null) {
            return null;
        }
        return new Artist(
                artistEntity.getId(),
                artistEntity.getNameGroup(),
                Artist.Genre.valueOf(artistEntity.getGenre().name()),
                artistEntity.getTimePerformances()
        );
    }
}
