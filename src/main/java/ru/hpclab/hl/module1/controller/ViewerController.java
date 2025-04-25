package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Viewer;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.mapper.ViewerMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import ru.hpclab.hl.module1.service.statistics.ObservabilityService;

@RestController
@RequestMapping("/viewers")
public class ViewerController {
    private final ViewerService viewerService;
    private final ObservabilityService observabilityService;
    @Autowired
    public ViewerController(ViewerService viewerService, ObservabilityService observabilityService) {
        this.viewerService = viewerService;
        this.observabilityService = observabilityService;
    }

    @GetMapping("")
    public List<Viewer> getViewer() {
        this.observabilityService.start(getClass().getSimpleName() + ":getViewer");
        List<Viewer> temp = viewerService.getAllViewers().stream()
                .map(ViewerMapper::toModel).collect(Collectors.toList());
        this.observabilityService.stop(getClass().getSimpleName() + ":getViewer");
        return temp;
    }
    @DeleteMapping("/clear")
    public void clearViewers() {
        this.observabilityService.start(getClass().getSimpleName() + ":clearViewers");
        viewerService.clearAllViewers(); // Метод для очистки всех зрителей
        this.observabilityService.stop(getClass().getSimpleName() + ":clearViewers");
    }
    @GetMapping("/{id}")
    public Viewer getViewerById(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":getViewerById");
        Viewer temp = ViewerMapper.toModel(viewerService.getViewerById(id));
        this.observabilityService.stop(getClass().getSimpleName() + ":getViewerById");
        return temp;
    }

    @DeleteMapping("/{id}")
    public void deletViewer(@PathVariable long id) {
        this.observabilityService.start(getClass().getSimpleName() + ":deletViewer");
        viewerService.deleteViewer(id);
        this.observabilityService.stop(getClass().getSimpleName() + ":deletViewer");
    }

    @PostMapping(value = "")
    public Viewer saveViewer(@RequestBody Viewer user) {
        this.observabilityService.start(getClass().getSimpleName() + ":saveViewer");
        Viewer temp = ViewerMapper.toModel(viewerService.saveViewer(ViewerMapper.toEntity(user)));
        this.observabilityService.stop(getClass().getSimpleName() + ":saveViewer");
        return temp;
    }

    @PutMapping(value = "/{id}")
    public Viewer updateViewer(@PathVariable(required = false) long id, @RequestBody Viewer user) {
        this.observabilityService.start(getClass().getSimpleName() + ":updateViewer");
        Viewer temp = ViewerMapper.toModel(viewerService.updateViewer(id, ViewerMapper.toEntity(user)));
        this.observabilityService.stop(getClass().getSimpleName() + ":updateViewer");
        return temp;
    }
}
