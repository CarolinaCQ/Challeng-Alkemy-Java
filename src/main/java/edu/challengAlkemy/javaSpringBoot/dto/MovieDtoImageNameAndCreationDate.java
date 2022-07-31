package edu.challengAlkemy.javaSpringBoot.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovieDtoImageNameAndCreationDate {
    
    private Long id;
    
    private String image;
    
    private String title;
    
    private LocalDate creationDate;
}
