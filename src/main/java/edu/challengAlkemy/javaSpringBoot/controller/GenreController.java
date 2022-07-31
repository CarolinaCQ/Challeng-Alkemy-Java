package edu.challengAlkemy.javaSpringBoot.controller;

import edu.challengAlkemy.javaSpringBoot.dto.GenreDto;
import edu.challengAlkemy.javaSpringBoot.entity.Genre;
import edu.challengAlkemy.javaSpringBoot.service.GenreService;
import edu.challengAlkemy.javaSpringBoot.service.MovieService;
import edu.challengAlkemy.javaSpringBoot.utility.ParamToDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final MovieService movieService;
    private final ParamToDto paramToDto;

    @PostMapping("/create")
    public ResponseEntity<Genre> createGenre(@RequestParam String name,
            @RequestParam List<Long> listMovieId,
            @RequestParam(required = false) MultipartFile image) {

        GenreDto dto = paramToDto.paramsToGenreDto(null, name, listMovieId);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.createGender(dto, image));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
