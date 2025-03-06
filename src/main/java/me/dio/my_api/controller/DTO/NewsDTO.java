package me.dio.my_api.controller.DTO;

import me.dio.my_api.domain.model.News;

public record NewsDTO(Long id, String icon, String description) {

    public NewsDTO(News model) {
        this(model.getId(), model.getIcon(), model.getDescription());
    }

    public News toModel() {
        News model = new News();

        model.setId(id);
        model.setIcon(icon);
        model.setDescription(description);
        return model;
    }
}
