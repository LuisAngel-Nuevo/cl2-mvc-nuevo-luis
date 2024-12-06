package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmCategoryPk implements Serializable {
    private Integer filmId;
    private Integer categoryId;
}
