package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.GenreDto;
import edu.challengAlkemy.javaSpringBoot.entity.Genre;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import edu.challengAlkemy.javaSpringBoot.mapper.GenreDtoToGenre;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.challengAlkemy.javaSpringBoot.repository.GenreRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreDtoToGenre mapper;
    private final ImageService imageService;

    @Override
    public Genre createGender(GenreDto dto, MultipartFile image) {
        if (genreRepository.existsByName(dto.getName())) {
            throw new CustomException("Invalid, this genre already exists", HttpStatus.BAD_REQUEST);
        }
        Genre genre = genreRepository.save(mapper.apply(dto));
        if (!Objects.isNull(image)) {
            genre.setImage(imageService.imageToString(image));
        }
        return genre;
    }

    @Override
    @Transactional
    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new CustomException("Invalid, this genre already exists", HttpStatus.NOT_FOUND);
        }
        genreRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMoviesByIdGenre(Long id) {
        return genreRepository.findMoviesByGenre(id);
    }

}
