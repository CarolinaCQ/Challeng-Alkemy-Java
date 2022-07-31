package edu.challengAlkemy.javaSpringBoot.repository;

import edu.challengAlkemy.javaSpringBoot.entity.Genre;
import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long>{
    
    @Query("SELECT m FROM Genre g JOIN g.listMovies m WHERE g.id=:id")
    public List<Movie> findMoviesByGenre(@Param("id") Long id);
    
    public boolean existsByName(String name);
}
