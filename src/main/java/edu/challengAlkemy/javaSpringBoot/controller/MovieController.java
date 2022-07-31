package edu.challengAlkemy.javaSpringBoot.controller;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.dto.MovieDtoImageNameAndCreationDate;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import edu.challengAlkemy.javaSpringBoot.service.CharacterService;
import edu.challengAlkemy.javaSpringBoot.service.MovieService;
import edu.challengAlkemy.javaSpringBoot.utility.ParamToDto;
import io.swagger.annotations.ApiParam;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final CharacterService characterService;
    private final ParamToDto paramToDto;

    @GetMapping
    public ResponseEntity<List<MovieDtoImageNameAndCreationDate>> getAllMovies(@RequestParam(value = "name", required = false) String title,
            @RequestParam(value = "genre", required = false) Long id,
            @RequestParam(value = "order", required = false) String order) {

        if (title != null) {
            if (order != null) {
                if (order.equalsIgnoreCase("asc")) {
                    return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByTitleAsc(title));
                }
                if (order.equalsIgnoreCase("desc")) {
                    return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByTitleDesc(title));
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByTitle(title));

        }

        if (id != null) {
            if (order != null) {
                if (order.equalsIgnoreCase("asc")) {
                    return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByGenreAsc(id));
                }
                if (order.equalsIgnoreCase("desc")) {
                    return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByGenreDesc(id));
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeriesByGenre(id));
        }

        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMoviesAndSeries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getByIdMovie(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getByIdMovieOrSerie(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestParam String title,
            @RequestParam @ApiParam(example = "2022-07-31") String creationDateString,
            @RequestParam Integer qualification,
            @RequestParam List<Long> listCharactersId,
            @RequestParam(required = false) MultipartFile image) {

        if (qualification < 1 || qualification > 5) {

        }
        LocalDate creationDate = LocalDate.parse(creationDateString);
        MovieDto dto = paramToDto.paramsToMovieDto(null, title, creationDate, qualification, listCharactersId);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.createMovieOrSerie(dto, image));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) @ApiParam(example = "2022-07-31") String creationDateString,
            @RequestParam(required = false) Integer qualification,
            @RequestParam(required = false) MultipartFile image) {

        LocalDate creationDate = LocalDate.parse(creationDateString);
        MovieDto dto = paramToDto.paramsToMovieDtoUpdate(id, title, creationDate, qualification);
        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovieOrSerie(dto, image));

    }

    @PutMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Movie> addCharactersToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.updateCharacters(idMovie, idCharacter));

    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Movie> removeCharactersToMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.removeCharacters(idMovie, idCharacter));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteCharacter(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
