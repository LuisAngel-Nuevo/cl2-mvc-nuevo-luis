package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto;

public record FilmDTO(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}
