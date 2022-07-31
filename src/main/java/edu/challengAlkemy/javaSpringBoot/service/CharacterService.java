package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.dto.CharacterDtoImageAndName;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import edu.challengAlkemy.javaSpringBoot.mapper.CharacterDtoToCharacter;
import edu.challengAlkemy.javaSpringBoot.mapper.CharacterDtoToCharacterUpdate;
import edu.challengAlkemy.javaSpringBoot.mapper.CharacterToCharacterDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.challengAlkemy.javaSpringBoot.repository.CharacterRepository;
import edu.challengAlkemy.javaSpringBoot.repository.MovieRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;
    private final CharacterDtoToCharacter mapper;
    private final CharacterDtoToCharacterUpdate mapperUpdate;
    private final CharacterToCharacterDto mapperGet;
    private final ImageService imageService;

    @Transactional(readOnly = true)
    public List<CharacterDtoImageAndName> getAllCharacters() {

        List<Character> listCharacters = characterRepository.findAll();

        List<CharacterDtoImageAndName> listCharactersDto = listCharacters.stream().map(mapperGet).collect(Collectors.toList());

        return listCharactersDto;
    }

    @Transactional(readOnly = true)
    public List<CharacterDtoImageAndName> getAllCharactersByName(String name) {
        List<Character> listCharacters = characterRepository.findByName(name);

        List<CharacterDtoImageAndName> listCharactersDto = listCharacters.stream().map(mapperGet).collect(Collectors.toList());

        return listCharactersDto;
    }

    @Transactional(readOnly = true)
    public List<CharacterDtoImageAndName> getAllCharactersByAge(Integer age) {
        List<Character> listCharacters = characterRepository.findByAge(age);

        List<CharacterDtoImageAndName> listCharactersDto = listCharacters.stream().map(mapperGet).collect(Collectors.toList());

        return listCharactersDto;
    }

    @Transactional(readOnly = true)
    public List<CharacterDtoImageAndName> getAllCharactersByWeight(Float weight) {
        List<Character> listCharacters = characterRepository.findByWeight(weight);

        List<CharacterDtoImageAndName> listCharactersDto = listCharacters.stream().map(mapperGet).collect(Collectors.toList());

        return listCharactersDto;
    }

    @Transactional(readOnly = true)
    public List<CharacterDtoImageAndName> getAllCharactersByMovies(Long id) {
        List<Character> listCharacters = characterRepository.findByMovies(id);

        List<CharacterDtoImageAndName> listCharactersDto = listCharacters.stream().map(mapperGet).collect(Collectors.toList());

        return listCharactersDto;
    }

    @Transactional(readOnly = true)
    public Character getByIdCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new CustomException("Character not found", HttpStatus.NOT_FOUND);
        }
        return characterRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Character> getAllCharactersById(List<Long> listCharactersId) {
        List<Character> listCharacters = listCharactersId.stream().map((id) -> getByIdCharacter(id)).collect(Collectors.toList());
        return listCharacters;
    }

    public Character createCharacter(CharacterDto dto, MultipartFile image) {
        Character character = characterRepository.save(mapper.apply(dto));
        if (!Objects.isNull(image)) {
            character.setImage(imageService.imageToString(image));
        }
        return character;
    }

    @Transactional
    public void movieAndSerieToListAdd(Long idCharacter, Movie movie) {
        if (!characterRepository.existsById(idCharacter)) {
            throw new CustomException("Character not found", HttpStatus.NOT_FOUND);
        }

        Character character = characterRepository.findById(idCharacter).get();

        List<Movie> listMovies = character.getListMovies();
        listMovies.add(movie);
    }

    @Transactional
    public void movieAndSerieToListRemove(Long idCharacter, Movie movie) {

        if (!characterRepository.existsById(idCharacter)) {
            throw new CustomException("Character not found", HttpStatus.NOT_FOUND);
        }

        Character character = characterRepository.findById(idCharacter).get();

        List<Movie> listMovies = character.getListMovies();
        listMovies.remove(movie);
    }

    @Transactional
    public Character updateCharacter(CharacterDto dto, MultipartFile image) {
        if (!characterRepository.existsById(dto.getId())) {
            throw new CustomException("Character not found", HttpStatus.NOT_FOUND);
        }
        Character character = characterRepository.save(mapperUpdate.apply(dto));
        if (!Objects.isNull(image)) {
            character.setImage(imageService.imageToString(image));
        }
        return character;
    }

    @Transactional
    public void deleteCharacter(Long id) {
        if (!characterRepository.existsById(id)) {
            throw new CustomException("Character not found", HttpStatus.NOT_FOUND);
        }
        characterRepository.deleteById(id);
    }

}
