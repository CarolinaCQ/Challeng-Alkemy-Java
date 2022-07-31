package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.GenreDto;
import edu.challengAlkemy.javaSpringBoot.entity.Genre;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoToGenre implements Function<GenreDto, Genre> {

    @Override
    public Genre apply(GenreDto dto) {

        Genre genre = new Genre();
        genre.setName(dto.getName());
        genre.setListMovies(dto.getListMovies());
        genre.setDelete(false);

        return genre;
    }

}
