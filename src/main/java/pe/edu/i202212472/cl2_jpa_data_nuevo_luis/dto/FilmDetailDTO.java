package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto;

import java.util.Date;

public record FilmDetailDTO(Integer filmId,
                            String title,
                            String description,
                            Integer releaseYear,
                            Integer languageId,
                            String languageName,
                            Integer rentalDuration,
                            Double rentalRate,
                            Integer length,
                            Double replacementCost,
                            String rating,
                            String specialFeatures,
                            Date lastUpdate) {
}
