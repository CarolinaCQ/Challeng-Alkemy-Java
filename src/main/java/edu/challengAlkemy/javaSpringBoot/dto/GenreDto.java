package edu.challengAlkemy.javaSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
    
    private Long id;
    
    private String name;
    
    @JsonIgnoreProperties("listCharacters")
    private List<Movie> listMovies;
}
