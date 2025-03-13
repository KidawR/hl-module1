package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Viewer;
import ru.hpclab.hl.module1.repository.ViewerRepository;

import java.util.List;
import java.util.UUID;

public class ViewerService {
    private final ViewerRepository viewerRepository;

    public ViewerService(ViewerRepository viewerRepository) {
        this.viewerRepository = viewerRepository;
    }

    public List<Viewer> getAllViewers() {
        return viewerRepository.findAll();
    }

    public Viewer getViewerById(String id) {
        return viewerRepository.findById(Long.getLong(id));
    }

    public Viewer saveViewer(Viewer viewer) {
        return viewerRepository.save(viewer);
    }

    public void deleteViewer(String id) {
        viewerRepository.delete(Long.getLong(id));
    }

    public Viewer updateViewer(String id, Viewer viewer) {
        viewer.setId(Long.getLong(id));
        return viewerRepository.put(viewer);
    }
}
