package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.ViewerEntity;
import ru.hpclab.hl.module1.repository.ViewerRepository;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
@Service
public class ViewerService {
    private final ViewerRepository viewerRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";

    public ViewerService(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    public List<ViewerEntity> getAllViewers() {
        return viewerRepository.findAll();
    }

    public ViewerEntity getViewerById(long id) {
        return viewerRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
    }

    public ViewerEntity saveViewer(ViewerEntity viewerEntity) {
        viewerEntity.setId(null);
        return viewerRepository.save(viewerEntity);
    }

    public void deleteViewer(long id) {
        viewerRepository.deleteById(id);
    }

    public ViewerEntity updateViewer(long id, ViewerEntity viewerEntity) {
        viewerEntity.setId(id);
        //when id is not empty save works with update logic
        return viewerRepository.save(viewerEntity);
    }
}
