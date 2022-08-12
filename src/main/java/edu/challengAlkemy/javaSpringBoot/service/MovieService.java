package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.dto.MovieDtoImageNameAndCreationDate;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface MovieService {
    
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeries();
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitleAsc(String title);
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitleDesc(String title);
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitle(String title);
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenreAsc(Long id);
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenreDesc(Long id);
    List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenre(Long id);
    Movie getByIdMovieOrSerie(Long id);
    Movie createMovieOrSerie(MovieDto dto, MultipartFile image);
    Movie updateMovieOrSerie(MovieDto dto, MultipartFile image);
    void deleteCharacter(Long id);
}
