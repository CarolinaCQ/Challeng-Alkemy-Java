package edu.challengAlkemy.javaSpringBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {
    
    private Long id;

    private String name;

    private Integer age;

    private Float weight;
    
    private String biography;
    
    

}
