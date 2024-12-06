package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

    @OneToMany(mappedBy = "actor")
    private List<FilmActor> filmActors;
}
