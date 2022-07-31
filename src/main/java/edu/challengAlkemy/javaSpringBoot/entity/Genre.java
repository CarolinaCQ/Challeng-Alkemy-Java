package edu.challengAlkemy.javaSpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@SQLDelete( sql = "UPDATE genres SET genre_delete = true WHERE genre_id = ?")
@Table(name = "genres")
public class Genre implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "genre_id")
    private Long id;
    
    @Column(name = "genre_name", nullable = false)
    private String name;
    
    @Column(name = "genre_image")
    private String image;
    
    @Column(name = "genre_delete")
    private Boolean delete;
    
    @ManyToMany
    @JoinTable(name="genres_movies_series",
    		   joinColumns=@JoinColumn(name="genre_id"),
    		   inverseJoinColumns=@JoinColumn(name="movies_series_id"))
    private List<Movie> listMovies;
}
