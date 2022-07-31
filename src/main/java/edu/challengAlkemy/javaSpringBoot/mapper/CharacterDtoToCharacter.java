package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoToCharacter implements Function<CharacterDto, Character> {

    @Override
    public Character apply(CharacterDto dto) {

        Character character = new Character();
        character.setName(dto.getName());
        character.setAge(dto.getAge());
        character.setWeight(dto.getWeight());
        character.setBiography(dto.getBiography());
        character.setDelete(false);

        return character;
    }

}
