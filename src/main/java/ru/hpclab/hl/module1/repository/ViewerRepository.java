package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hpclab.hl.module1.entity.ViewerEntity;

import java.util.*;

public interface ViewerRepository extends CrudRepository<ViewerEntity, Long> {

    @Override
    List<ViewerEntity> findAll();

}