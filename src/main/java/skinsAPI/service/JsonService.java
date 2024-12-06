package skinsAPI.service;

import skinsAPI.domain.entities.Skin;

import java.util.List;

public interface JsonService {

    Skin findById(Long id);

    List<Skin> findAll();

}
