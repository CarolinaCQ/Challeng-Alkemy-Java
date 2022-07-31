package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import edu.challengAlkemy.javaSpringBoot.repository.CharacterRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@Component
@RequiredArgsConstructor
public class CharacterDtoToCharacterUpdate implements Function<CharacterDto, Character> {

    private final CharacterRepository characterRepository;

    @Override
    public Character apply(CharacterDto dto) {

        if (!characterRepository.existsById(dto.getId())) {
            throw new CustomException("Chracter not found", HttpStatus.NOT_FOUND);
        }

        Character character = characterRepository.findById(dto.getId()).get();
        if (!Objects.isNull(dto.getName())) {
            character.setName(dto.getName());
        }
        if (!Objects.isNull(dto.getAge())) {
            character.setAge(dto.getAge());
        }
        if (!Objects.isNull(dto.getWeight())) {
            character.setWeight(dto.getWeight());
        }
        if (!Objects.isNull(dto.getBiography())) {
            character.setBiography(dto.getBiography());
        }

        return character;
    }
}
