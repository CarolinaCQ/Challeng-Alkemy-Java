package edu.challengAlkemy.javaSpringBoot.repository;

import edu.challengAlkemy.javaSpringBoot.entity.Character;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    
    public List<Character> findByName(String name);
    
    public List<Character> findByAge(Integer age);
    
    public List<Character> findByWeight(Float weight);
    
    @Query("SELECT c FROM Character c JOIN c.listMovies m WHERE m.id=:id")
    public List<Character> findByMovies(@Param("id") Long id);
}
