package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.CustomException;
import ru.hpclab.hl.module1.model.Viewer;

import java.util.*;

import static java.lang.String.format;

@Repository
public class ViewerRepository {
    public static final String VIEWER_NOT_FOUND_MSG = "Viewer with ID %s not found";
    public static final String VIEWER_EXISTS_MSG = "Viewer with ID %s is already exists";

    private final Map<Long, Viewer> viewers = new HashMap<>();

    public List<Viewer> findAll() {
        return new ArrayList<>(viewers.values());
    }

    public Viewer findById(long id) {
        final var viewer = viewers.get(id);
        if (viewer == null) {
            throw new CustomException(format(VIEWER_NOT_FOUND_MSG, id));
        }
        return viewer;
    }

    public void delete(long id) {
        final var removed = viewers.remove(id);
        if (removed == null) {
            throw new CustomException(format(VIEWER_NOT_FOUND_MSG, id));
        }
    }

    public Viewer save(Viewer viewer) {
        if (ObjectUtils.isEmpty(viewer.getId())) {
            viewer.setId(new Random().nextLong());
        }

        final var viewerData = viewers.get(viewer.getId());
        if (viewerData != null) {
            throw new CustomException(format(VIEWER_EXISTS_MSG, viewer.getId()));
        }

        viewers.put(viewer.getId(), viewer);

        return viewer;
    }

    public Viewer put(Viewer viewer) {
        final var userData = viewers.get(viewer.getId());
        if (userData == null) {
            throw new CustomException(format(VIEWER_NOT_FOUND_MSG, viewer.getId()));
        }

        final var removed = viewers.remove(viewer.getId());
        if (removed != null) {
            viewers.put(viewer.getId(), viewer);
        } else {
            throw new CustomException(format(VIEWER_NOT_FOUND_MSG, viewer.getId()));
        }

        return viewer;
    }

    public void clear(){
        viewers.clear();
    }
}
