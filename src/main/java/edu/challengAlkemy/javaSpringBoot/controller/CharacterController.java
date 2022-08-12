package edu.challengAlkemy.javaSpringBoot.controller;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.dto.CharacterDtoImageAndName;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import edu.challengAlkemy.javaSpringBoot.service.CharacterServiceImpl;
import edu.challengAlkemy.javaSpringBoot.utility.ParamToDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterServiceImpl characterService;
    private final ParamToDto paramToDto;

    @GetMapping
    public ResponseEntity<List<CharacterDtoImageAndName>> getAllCharacters(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "movies", required = false) Long id,
            @RequestParam(value = "weight", required = false) Float weight) {

        if (name != null) {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharactersByName(name));
        }
        if (age != null) {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharactersByAge(age));
        }
        if (weight != null) {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharactersByWeight(weight));
        }

        if (id != null) {
            return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharactersByMovies(id));
        }

        return ResponseEntity.status(HttpStatus.OK).body(characterService.getAllCharacters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getByIdCharacter(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(characterService.getByIdCharacter(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Character> createCharacter(@RequestParam String name,
            @RequestParam String biography,
            @RequestParam Integer age,
            @RequestParam Float weight,
            @RequestParam(required = false) MultipartFile image) {
        CharacterDto dto = paramToDto.paramsToCharacterDto(null, name, age, weight, biography);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterService.createCharacter(dto, image));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String biography,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Float weight,
            @RequestParam(required = false) MultipartFile image) {

        CharacterDto dto = paramToDto.paramsToCharacterDto(id, name, age, weight, biography);
        return ResponseEntity.status(HttpStatus.OK).body(characterService.updateCharacter(dto, image));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
