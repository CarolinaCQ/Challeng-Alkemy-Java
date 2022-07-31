package edu.challengAlkemy.javaSpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Setter
@Getter
@NoArgsConstructor
@SQLDelete( sql = "UPDATE movies_series SET movies_series_delete = true WHERE movies_series_id = ?")
@Table(name = "movies_series")
public class Movie implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "movies_series_id")
    private Long id;
    
    @Column(name = "movies_series_image")
    private String image;
    
    @Column(name = "movies_series_title", nullable = false)
    private String title;
    
    @Column(name = "movies_series_creation_date", nullable = false)
    private LocalDate creationDate;
    
    @Column(name = "movies_series_qualification", nullable = false)
    private Integer qualification;
    
    @Column(name = "movies_series_delete")
    private Boolean delete;
    
    @JsonIgnoreProperties("listMovies")
    @ManyToMany(mappedBy="listMovies")
    private List<Character> listCharacters;
}
