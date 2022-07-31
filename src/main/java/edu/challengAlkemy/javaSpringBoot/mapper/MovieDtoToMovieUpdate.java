package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import edu.challengAlkemy.javaSpringBoot.repository.MovieRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;

@Component
@RequiredArgsConstructor
public class MovieDtoToMovieUpdate implements Function<MovieDto, Movie> {

    private final MovieRepository movieRepository;

    @Override
    public Movie apply(MovieDto dto) {

        if (!movieRepository.existsById(dto.getId())) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.findById(dto.getId()).get();
        if (!Objects.isNull(dto.getTitle())) {
            movie.setTitle(dto.getTitle());
        }
        if (!Objects.isNull(dto.getCreationDate())) {
            movie.setCreationDate(dto.getCreationDate());
        }
        if (!Objects.isNull(dto.getQualification())) {
            movie.setQualification(dto.getQualification());
        }

        return movie;
    }

}
