package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.entity.ViewerEntity;
import ru.hpclab.hl.module1.model.Viewer;

public class ViewerMapper {
    private ViewerMapper() {
    }

    public static ViewerEntity toEntity(Viewer viewer) {
        if (viewer == null) {
            return null;
        }
        return new ViewerEntity(
                null,  // ID автоматически генерируется
                viewer.getName(),
                viewer.getEmail()
        );
    }

    public static Viewer toModel(ViewerEntity viewerEntity) {
        if (viewerEntity == null) {
            return null;
        }
        return new Viewer(
                viewerEntity.getId(),
                viewerEntity.getName(),
                viewerEntity.getEmail()
        );
    }
}
