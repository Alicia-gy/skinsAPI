package skinsAPI.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skinsAPI.domain.entities.Skin;
import skinsAPI.exceptions.JsonFileException;
import skinsAPI.exceptions.SkinNotFoundException;
import skinsAPI.service.JsonService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JsonServiceImpl implements JsonService {

    private static final String filepath = "src/main/resources/skin_data.json";


    @Override
    public Skin findById(Long id) {
        List<Skin> skins = readFile();

        return skins.stream()
                .filter(skin -> skin.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new SkinNotFoundException("Skin not found: invalid id"));
    }

    @Override
    public List<Skin> findAll() {
        return readFile();
    }


    private static List<Skin> readFile() {
            File jsonFile = new File(filepath);
            List<Skin> skinsFromFile;

            if(jsonFile.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    skinsFromFile = objectMapper.readValue(jsonFile, new TypeReference<>() {});
                } catch (IOException e) {
                    throw new JsonFileException("Invalid File");
                }
            }
            else {
                throw new JsonFileException("File not found");
            }

            return skinsFromFile;
        }
}
