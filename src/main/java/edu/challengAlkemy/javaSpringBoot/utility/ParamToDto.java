package edu.challengAlkemy.javaSpringBoot.utility;

import edu.challengAlkemy.javaSpringBoot.dto.CharacterDto;
import edu.challengAlkemy.javaSpringBoot.dto.GenreDto;
import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import edu.challengAlkemy.javaSpringBoot.service.CharacterService;
import edu.challengAlkemy.javaSpringBoot.service.MovieService;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ParamToDto {

    private final CharacterService characterService;
    private final MovieService movieService;

    public CharacterDto paramsToCharacterDto(Long id, String name, Integer age, Float weight, String biography) {
        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(id);
        characterDto.setName(name);
        if (!Objects.isNull(age)) {
            if (age <= 0) {
                throw new CustomException("Invalidad, the age must be greater than zero", HttpStatus.BAD_REQUEST);
            } else {
                characterDto.setAge(age);
            }
        }
        if (!Objects.isNull(weight)) {
            if (weight <= 0) {
                throw new CustomException("Invalidad, the weight must be greater than zero", HttpStatus.BAD_REQUEST);
            } else {
                characterDto.setWeight(weight);
            }
        }
        characterDto.setBiography(biography);
        return characterDto;
    }

    public MovieDto paramsToMovieDto(Long id, String title, LocalDate creationDate, Integer qualification, List<Long> listCharactersId) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);
        movieDto.setTitle(title);
        movieDto.setCreationDate(creationDate);
        if (qualification < 1 || qualification > 5) {
            throw new CustomException("Invalidad, the qualification must be a number between one and five", HttpStatus.BAD_REQUEST);
        } else {
            movieDto.setQualification(qualification);
        }
        movieDto.setListCharacters(characterService.getAllCharactersById(listCharactersId));
        return movieDto;
    }

    public MovieDto paramsToMovieDtoUpdate(Long id, String title, LocalDate creationDate, Integer qualification) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);
        movieDto.setTitle(title);
        movieDto.setCreationDate(creationDate);
        if (!Objects.isNull(qualification)) {
            if (qualification < 1 || qualification > 5) {
                throw new CustomException("Invalidad, the qualification must be a number between one and five", HttpStatus.BAD_REQUEST);
            } else {
                movieDto.setQualification(qualification);
            }
        }
        return movieDto;
    }

    public GenreDto paramsToGenreDto(Long id, String name, List<Long> listMovieId) {
        GenreDto genreDto = new GenreDto();
        genreDto.setId(id);
        genreDto.setName(name);
        genreDto.setListMovies(movieService.getAllMoviesAndSeriesById(listMovieId));
        return genreDto;
    }
}
