package me.dio.my_api.controller.DTO;

import me.dio.my_api.domain.model.Feature;

public record FeatureDTO(Long id, String icon, String description) {

    public FeatureDTO(Feature model) {
        this(model.getId(), model.getIcon(), model.getDescription());
    }

    public Feature toModel() {
        Feature model = new Feature();

        model.setId(id);
        model.setIcon(icon);
        model.setDescription(description);
        return model;
    }
}
