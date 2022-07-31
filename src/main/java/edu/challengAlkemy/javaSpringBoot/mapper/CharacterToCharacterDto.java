package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDtoImageAndName;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class CharacterToCharacterDto implements Function<Character, CharacterDtoImageAndName> {

    @Override
    public CharacterDtoImageAndName apply(Character character) {

        CharacterDtoImageAndName dto = new CharacterDtoImageAndName();
        dto.setId(character.getId());
        dto.setImage(character.getImage());
        dto.setName(character.getName());

        return dto;
    }

}
