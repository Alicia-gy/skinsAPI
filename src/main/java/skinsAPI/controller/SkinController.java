package skinsAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skinsAPI.domain.dtos.request.ColourUpdateRequest;
import skinsAPI.domain.entities.Skin;
import skinsAPI.service.JsonService;
import skinsAPI.service.SkinService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/skins")
public class SkinController {

    private final SkinService skinService;
    private final JsonService jsonService;

    @GetMapping("/available")
    public ResponseEntity<?> availableSkins() {
        List<Skin> skins = jsonService.findAll();

        String convertedList = skins.stream()
                .map(Skin::toString)
                .collect(Collectors.joining("\n\n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<?> buySkin(@PathVariable(value = "id") Long id) {
        Skin skin = jsonService.findById(id);

        skinService.create(skin);
        return new ResponseEntity<>(skin.toString(), HttpStatus.OK);
    }

    @GetMapping("/myskins")
    public ResponseEntity<?> ownedSkins() {
        List<Skin> skins = skinService.findAll();

        String convertedList = skins.stream()
                .map(Skin::toString)
                .collect(Collectors.joining("\n\n"));

        return new ResponseEntity<>(convertedList, HttpStatus.OK);
    }

    @PutMapping("/colour/{id}")
    public ResponseEntity<?> updateColour (@RequestBody ColourUpdateRequest request,
                                           @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                skinService.update(request, id).toString(),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkin(@PathVariable(value = "id") Long id) {
        skinService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getskin/{id]")
    public ResponseEntity<?> getSkin(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(
                skinService.findById(id).toString(),
                HttpStatus.OK);
    }





}
