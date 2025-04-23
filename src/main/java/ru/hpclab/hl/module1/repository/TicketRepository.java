package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hpclab.hl.module1.entity.TicketEntity;

import java.util.*;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    @Override
    List<TicketEntity> findAll();
}