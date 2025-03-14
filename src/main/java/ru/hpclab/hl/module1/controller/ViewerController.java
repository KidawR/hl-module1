package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.model.Viewer;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.mapper.ViewerMapper;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viewers")
public class ViewerController {
    private final ViewerService viewerService;

    @Autowired
    public ViewerController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("")
    public List<Viewer> getViewer() {
        return viewerService.getAllViewers().stream()
                .map(ViewerMapper::toModel).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Viewer getViewerById(@PathVariable long id) {
        return ViewerMapper.toModel(viewerService.getViewerById(id));
    }

    @DeleteMapping("/{id}")
    public void deletViewer(@PathVariable long id) {
        viewerService.deleteViewer(id);
    }

    @PostMapping(value = "")
    public Viewer saveViewer(@RequestBody Viewer user) {
        return ViewerMapper.toModel(viewerService.saveViewer(ViewerMapper.toEntity(user)));
    }

    @PutMapping(value = "/{id}")
    public Viewer updateViewer(@PathVariable(required = false) long id, @RequestBody Viewer user) {
        return ViewerMapper.toModel(viewerService.updateViewer(id, ViewerMapper.toEntity(user)));
    }
}
