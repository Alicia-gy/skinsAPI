package skinsAPI.service;

import skinsAPI.domain.dtos.request.ColourUpdateRequest;
import skinsAPI.domain.entities.Skin;

import java.util.List;

public interface SkinService {

    Skin create(Skin skin);

    Skin update(ColourUpdateRequest request, Long id);

    Skin findById(Long id);

    List<Skin> findAll();

    void deleteById(Long id);
}
