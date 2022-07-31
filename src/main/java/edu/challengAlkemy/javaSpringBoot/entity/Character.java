package edu.challengAlkemy.javaSpringBoot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
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
@SQLDelete( sql = "UPDATE characters SET character_delete = true WHERE character_id = ?")
@Table(name = "characters")
public class Character implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "character_id")
    private Long id;

    @Column(name = "character_image")
    private String image;

    @Column(name = "character_name", nullable = false)
    private String name;

    @Column(name = "character_age", nullable = false)
    private Integer age;

    @Column(name = "character_weight", nullable = false)
    private Float weight;

    @Column(name = "character_biography", nullable = false)
    private String biography;

    @Column(name = "character_delete")
    private Boolean delete;

    @JsonIgnoreProperties("listCharacters")
    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "characters_movies_series",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_series_id"))
    private List<Movie> listMovies;
}
