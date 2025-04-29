package ru.hpclab.hl.module1.service;


import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.entity.ViewerEntity;
import ru.hpclab.hl.module1.repository.ViewerRepository;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
@Service
public class ViewerService {
    private final ViewerRepository viewerRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";
    private final ObservabilityService observabilityService;
    public ViewerService(ViewerRepository viewerRepository, ObservabilityService observabilityService) {
        this.viewerRepository = viewerRepository;
        this.observabilityService = observabilityService;
    }

    public List<ViewerEntity> getAllViewers() {
        this.observabilityService.start(getClass().getSimpleName() + ":getAllViewers");
        List<ViewerEntity> temp = viewerRepository.findAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":getAllViewers");
        return temp;
    }

    public ViewerEntity getViewerById(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getViewerById");
        ViewerEntity temp = viewerRepository.findById(id).orElseThrow(() -> new CustomException(format(USER_NOT_FOUND_MSG, id)));
        this.observabilityService.stop(getClass().getSimpleName() + ":getViewerById");
        return temp;
    }

    public ViewerEntity saveViewer(ViewerEntity viewerEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveViewer");
        viewerEntity.setId(null);
        ViewerEntity temp = viewerRepository.save(viewerEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":saveViewer");
        return temp;
    }

    public void deleteViewer(long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deleteViewer");
        viewerRepository.deleteById(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deleteViewer");
    }

    public ViewerEntity updateViewer(long id, ViewerEntity viewerEntity) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateViewer");
        viewerEntity.setId(id);
        //when id is not empty save works with update logic
        ViewerEntity temp = viewerRepository.save(viewerEntity);
        this.observabilityService.stop(getClass().getSimpleName() + ":updateViewer");
        return temp;
    }

    public void clearAllViewers() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearAllViewers");
        viewerRepository.deleteAll();
        this.observabilityService.stop(getClass().getSimpleName() + ":clearAllViewers");
    }
}
