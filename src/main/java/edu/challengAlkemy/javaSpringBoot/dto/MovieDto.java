package edu.challengAlkemy.javaSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    
    private Long id;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd", shape = Shape.STRING)
    private LocalDate creationDate;

    private Integer qualification;

    @JsonIgnoreProperties("listMoviesAndSeries")
    private List<Character> listCharacters;

    public MovieDto(Long id, String title, LocalDate creationDate, Integer qualification) {
        this.id = id;
        this.title = title;
        this.creationDate = creationDate;
        this.qualification = qualification;
    }
    
    
}
