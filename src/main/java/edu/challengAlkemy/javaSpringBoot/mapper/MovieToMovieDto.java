/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.challengAlkemy.javaSpringBoot.mapper;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDtoImageNameAndCreationDate;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.function.Function;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieDto implements Function<Movie, MovieDtoImageNameAndCreationDate> {

    @Override
    public MovieDtoImageNameAndCreationDate apply(Movie movie) {

        MovieDtoImageNameAndCreationDate dto = new MovieDtoImageNameAndCreationDate();
        dto.setId(movie.getId());
        dto.setImage(movie.getImage());
        dto.setTitle(movie.getTitle());
        dto.setCreationDate(movie.getCreationDate());

        return dto;
    }

}
