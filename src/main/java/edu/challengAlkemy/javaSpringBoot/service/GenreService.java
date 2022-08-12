package edu.challengAlkemy.javaSpringBoot.service;

import edu.challengAlkemy.javaSpringBoot.dto.GenreDto;
import edu.challengAlkemy.javaSpringBoot.entity.Genre;
import org.springframework.web.multipart.MultipartFile;

public interface GenreService {
    
    Genre createGender(GenreDto dto, MultipartFile image);
    void deleteGenre(Long id);
}
