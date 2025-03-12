package ru.hpclab.hl.module1.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.model.User;

import java.util.*;

import static java.lang.String.format;

@Repository
public class ViewerRepository {
    public static final String VIEWER_NOT_FOUND_MSG = "Viewer with ID %s not found";
    public static final String VIEWER_EXISTS_MSG = "Viewer with ID %s is already exists";

    private final Map<UUID, viewer> viewers = new HashMap<>();

    public List<viewer> findAll() {
        return new ArrayList<>(viewers.values());
    }

    public viewer findById(UUID id) {
        final var viewer = viewers.get(id);
        if (viewer == null) {
            throw new UserException(format(VIEWER_NOT_FOUND_MSG, id));
        }
        return viewer;
    }

    public void delete(UUID id) {
        final var removed = viewers.remove(id);
        if (removed == null) {
            throw new UserException(format(VIEWER_NOT_FOUND_MSG, id));
        }
    }

    public User save(User user) {
        if (ObjectUtils.isEmpty(user.getIdentifier())) {
            user.setIdentifier(UUID.randomUUID());
        }

        final var userData = viewers.get(user.getIdentifier());
        if (userData != null) {
            throw new UserException(format(VIEWER_EXISTS_MSG, user.getIdentifier()));
        }

        viewers.put(user.getIdentifier(), user);

        return user;
    }

    public User put(User user) {
        final var userData = viewers.get(user.getIdentifier());
        if (userData == null) {
            throw new UserException(format(VIEWER_NOT_FOUND_MSG, user.getIdentifier()));
        }

        final var removed = viewers.remove(user.getIdentifier());
        if (removed != null) {
            viewers.put(user.getIdentifier(), viewer);
        } else {
            throw new UserException(format(VIEWER_NOT_FOUND_MSG, user.getIdentifier()));
        }

        return user;
    }

    public void clear(){
        viewers.clear();
    }
}
