package edu.challengAlkemy.javaSpringBoot.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete( sql = "UPDATE users SET user_delete = true WHERE user_id = ?")
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    private Long id;
    
    @Column(name="user_username", unique=true, nullable=false)
    private String username;
    
    @Column(name="user_password", nullable=false)
    private String password;
    
    @Column(name = "user_delete")
    private Boolean delete;
    
    @ManyToMany(fetch = EAGER)
    private List<Role> roles;
}
