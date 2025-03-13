package ru.hpclab.hl.module1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.model.Viewer;

import java.util.List;
@RestController
public class ViewerController {
    private final ViewerService viewerService;

    @Autowired
    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("/viewer")
    public List<Viewer> getViewer() {
        return viewerService.getAllViewers();
    }

    @GetMapping("/viewer/{id}")
    public Viewer getViewerById(@PathVariable String id) {
        return viewerService.getViewerById(id);
    }

    @DeleteMapping("/viewer/{id}")
    public void deleteViewer(@PathVariable String id) {
        viewerService.deleteViewer(id);
    }

    @PostMapping(value = "/viewer/")
    public Viewer saveViewer(@RequestBody Viewer viewer) {
        return viewerService.saveViewer(viewer);
    }

    @PutMapping(value = "/viewer/{id}")
    public Viewer updateViewer(@PathVariable(required = false) String id, @RequestBody Viewer viewer) {
        return viewerService.updateViewer(id, viewer);
    }
}
