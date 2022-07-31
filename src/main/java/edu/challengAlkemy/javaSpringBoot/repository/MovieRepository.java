package edu.challengAlkemy.javaSpringBoot.repository;

import edu.challengAlkemy.javaSpringBoot.entity.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.title=:title ORDER BY m.creationDate ASC")
    public List<Movie> findByTitleAsc(@Param("title") String title);

    @Query("SELECT m FROM Movie m WHERE m.title=:title ORDER BY m.creationDate DESC")
    public List<Movie> findByTitleDesc(@Param("title") String title);
    
    @Query("SELECT m FROM Movie m WHERE m.title=:title")
    public List<Movie> findByTitle(@Param("title") String title);

    @Query(value = "SELECT M.movies_series_id, M.movies_series_title, M.movies_series_image, M.movies_series_creation_date, M.movies_series_qualification, M.movies_series_delete FROM movies_series AS M\n"
            + "\nINNER JOIN genres_movies_series AS R ON M.movies_series_id=R.movies_series_id\n"
            + "\nINNER JOIN genres AS G ON R.genre_id=G.genre_id\n"
            + "\nWHERE G.genre_id=?1 ORDER BY M.movies_series_creation_date ASC ;", nativeQuery = true)
    public List<Movie> findByGenreAsc(Long id);

    @Query(value = "SELECT M.movies_series_id, M.movies_series_title, M.movies_series_image, M.movies_series_creation_date, M.movies_series_qualification, M.movies_series_delete FROM movies_series AS M\n"
            + "INNER JOIN genres_movies_series AS R ON M.movies_series_id=R.movies_series_id\n"
            + "INNER JOIN genres AS G ON R.genre_id=G.genre_id\n"
            + "WHERE G.genre_id=?1 ORDER BY M.movies_series_creation_date DESC ;", nativeQuery = true)
    public List<Movie> findByGenreDesc(Long id);
    
    @Query(value = "SELECT M.movies_series_id, M.movies_series_title, M.movies_series_image, M.movies_series_creation_date, M.movies_series_qualification, M.movies_series_delete FROM movies_series AS M\n"
            + "INNER JOIN genres_movies_series AS R ON M.movies_series_id=R.movies_series_id\n"
            + "INNER JOIN genres AS G ON R.genre_id=G.genre_id\n"
            + "WHERE G.genre_id=?1 ;", nativeQuery = true)
    public List<Movie> findByGenre(Long id);

   
    
}
