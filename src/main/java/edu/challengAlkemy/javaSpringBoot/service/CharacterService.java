package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.dto.CharacterDtoImageAndName;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CharacterService {
    
    List<CharacterDtoImageAndName> getAllCharacters();
    List<CharacterDtoImageAndName> getAllCharactersByName(String name);
    List<CharacterDtoImageAndName> getAllCharactersByAge(Integer age);
    List<CharacterDtoImageAndName> getAllCharactersByWeight(Float weight);
    List<CharacterDtoImageAndName> getAllCharactersByMovies(Long id);
    Character getByIdCharacter(Long id);
    Character createCharacter(CharacterDto dto, MultipartFile image);
    Character updateCharacter(CharacterDto dto, MultipartFile image);
    void deleteCharacter(Long id);
}
