package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.MovieDto;
import edu.challengAlkemy.javaSpringBoot.dto.MovieDtoImageNameAndCreationDate;
import edu.challengAlkemy.javaSpringBoot.entity.Character;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import edu.challengAlkemy.javaSpringBoot.exception.CustomException;
import edu.challengAlkemy.javaSpringBoot.mapper.MovieDtoToMovie;
import edu.challengAlkemy.javaSpringBoot.mapper.MovieDtoToMovieUpdate;
import edu.challengAlkemy.javaSpringBoot.mapper.MovieToMovieDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.challengAlkemy.javaSpringBoot.repository.MovieRepository;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieDtoToMovie mapper;
    private final MovieDtoToMovieUpdate mapperUpdate;
    private final MovieToMovieDto mapperGet;
    private final CharacterServiceImpl characterService;
    private final GenreServiceImpl genreService;
    private final ImageService imageService;

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeries() {
        List<Movie> listMovies = movieRepository.findAll();

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitleAsc(String title) {
        List<Movie> listMovies = movieRepository.findByTitleAsc(title);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitleDesc(String title) {
        List<Movie> listMovies = movieRepository.findByTitleDesc(title);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByTitle(String title) {
        List<Movie> listMovies = movieRepository.findByTitle(title);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenreAsc(Long id) {
        List<Movie> listMovies = movieRepository.findByGenreAsc(id);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenreDesc(Long id) {
        List<Movie> listMovies = movieRepository.findByGenreDesc(id);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public List<MovieDtoImageNameAndCreationDate> getAllMoviesAndSeriesByGenre(Long id) {
        List<Movie> listMovies = movieRepository.findByGenre(id);

        List<MovieDtoImageNameAndCreationDate> listMoviesDto = listMovies.stream().map(mapperGet).collect(Collectors.toList());

        return listMoviesDto;
    }

    @Override
    @Transactional(readOnly=true)
    public Movie getByIdMovieOrSerie(Long id) {
        return movieRepository.findById(id).get();
    }

    @Override
    public Movie createMovieOrSerie(MovieDto dto, MultipartFile image) {
        Movie movie = mapper.apply(dto);
        if (!Objects.isNull(image)) {
            movie.setImage(imageService.imageToString(image));
        }

        movie = movieRepository.save(movie);

        addMovieToCharacter(movie.getId());

        return movie;
    }

    @Override
    @Transactional
    public Movie updateMovieOrSerie(MovieDto dto, MultipartFile image) {
        if (!movieRepository.existsById(dto.getId())) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }
        Movie movie = movieRepository.save(mapperUpdate.apply(dto));
        if (!Objects.isNull(image)) {
            movie.setImage(imageService.imageToString(image));
        }
        return movie;
    }

    @Override
    @Transactional
    public void deleteCharacter(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }
        movieRepository.deleteById(id);
    }
    
        @Transactional(readOnly = true)
    public List<Movie> getAllMoviesAndSeriesById(List<Long> listMoviesId) {
        List<Movie> listMovies = listMoviesId.stream().map((id) -> getByIdMovieOrSerie(id)).collect(Collectors.toList());
        return listMovies;
    }

    @Transactional
    private void addMovieToCharacter(Long idMovie) {

        if (!movieRepository.existsById(idMovie)) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.findById(idMovie).get();

        List<Character> listCharacters = movie.getListCharacters();

        for (Character c : listCharacters) {
            c.getListMovies().add(movie);

        }
    }


    @Transactional
    public Movie updateCharacters(Long idMovie, Long idCharacter) {

        if (!movieRepository.existsById(idMovie)) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.findById(idMovie).get();

        List<Character> listCharacters = movie.getListCharacters();
        listCharacters.add(characterService.getByIdCharacter(idCharacter));

        characterService.movieAndSerieToListAdd(idCharacter, movie);

        return movieRepository.save(movie);
    }

    @Transactional
    public Movie removeCharacters(Long idMovie, Long idCharacter) {

        if (!movieRepository.existsById(idMovie)) {
            throw new CustomException("Movie not found", HttpStatus.NOT_FOUND);
        }

        Movie movie = movieRepository.findById(idMovie).get();

        List<Character> listCharacters = movie.getListCharacters();
        listCharacters.remove(characterService.getByIdCharacter(idCharacter));

        characterService.movieAndSerieToListRemove(idCharacter, movie);

        return movieRepository.save(movie);
    }

}
