package ru.hpclab.hl.module1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.service.ViewerService;
import ru.hpclab.hl.module1.model.Viewer;

import java.util.List;
public class ViewerController {
    private final ViewerService viewerService;

    @Autowired
    public UserController(ViewerService viewerService) {
        this.viewerService = viewerService;
    }

    @GetMapping("/users")
    public List<Viewer> getUsers() {
        return viewerService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public Viewer getUserById(@PathVariable String id) {
        return viewerService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable String id) {
        viewerService.deleteUser(id);
    }

    @PostMapping(value = "/users/")
    public Viewer saveUser(@RequestBody Viewer client) {
        return ViewerService.saveUser(client);
    }

    @PutMapping(value = "/users/{id}")
    public Viewer updateUser(@PathVariable(required = false) String id, @RequestBody Viewer viewer) {
        return viewerService.updateUser(id, viewer);
    }
}
