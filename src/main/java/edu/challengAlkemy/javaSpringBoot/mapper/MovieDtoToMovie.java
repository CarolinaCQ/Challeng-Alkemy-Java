package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoToMovie implements Function<MovieDto, Movie> {

    @Override
    public Movie apply(MovieDto dto) {

        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setCreationDate(dto.getCreationDate());
        movie.setQualification(dto.getQualification());
        movie.setListCharacters(dto.getListCharacters());
        movie.setDelete(false);

        return movie;
    }

}
