package skinsAPI.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skinsAPI.domain.dtos.request.ColourUpdateRequest;
import skinsAPI.domain.entities.Skin;
import skinsAPI.exceptions.SkinNotFoundException;
import skinsAPI.repository.SkinRepository;
import skinsAPI.service.SkinService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkinServiceImpl implements SkinService {

    private final SkinRepository skinRepository;


    @Override
    public Skin create(Skin skin) {
        skinRepository.save(skin);
        return skin;
    }

    @Override
    public Skin update(ColourUpdateRequest request, Long id) {
        Skin skin = skinRepository.findById(id)
                .orElseThrow(() -> new SkinNotFoundException("Skin not found: invalid id"));

        skin.setColour(request.getColour());
        skinRepository.save(skin);
        return skin;
    }

    @Override
    public Skin findById(Long id) {
        return skinRepository.findById(id)
                .orElseThrow(() -> new SkinNotFoundException("Skin not found: invalid id"));
    }

    @Override
    public List<Skin> findAll() {
        return skinRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        skinRepository.deleteById(id);
    }
}
