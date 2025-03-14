package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hpclab.hl.module1.entity.ArtistEntity;

import java.util.*;

public interface ArtistRepository extends CrudRepository<ArtistEntity, Long> {

    List<ArtistEntity> findAll();

}