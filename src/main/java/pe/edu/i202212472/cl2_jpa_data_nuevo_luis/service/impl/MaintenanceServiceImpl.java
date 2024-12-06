package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDetailDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmInsertDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Film;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Inventory;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Language;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Rental;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository.FilmRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository.InventoryRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository.LanguageRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository.RentalRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public List<FilmDTO> findAllFilms() {
        List<FilmDTO> films = new ArrayList<FilmDTO>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDTO filmDTO = new FilmDTO(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDTO);
        });
        return films;
    }

    @Override
    public FilmDetailDTO findFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDTO(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public Boolean updateFilm(FilmDetailDTO filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        Language language = languageRepository.findById(filmDetailDto.languageId())
                .orElseThrow(() -> new RuntimeException("Language not found"));
        return optional.map(
                film -> {
                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setLanguage(language);
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }

    @Override
    public void registerFilm(FilmInsertDTO filmInsertDto) {
        Film film = new Film();
        film.setTitle(filmInsertDto.title());
        film.setDescription(filmInsertDto.description());
        film.setReleaseYear(filmInsertDto.releaseYear());
        film.setRentalDuration(filmInsertDto.rentalDuration());
        film.setRentalRate(filmInsertDto.rentalRate());
        film.setLength(filmInsertDto.length());
        film.setReplacementCost(filmInsertDto.replacementCost());
        film.setRating(filmInsertDto.rating());
        film.setSpecialFeatures(filmInsertDto.specialFeatures());
        Language language = languageRepository.findById(filmInsertDto.languageId())
                .orElseThrow(() -> new IllegalArgumentException("Idioma no válido"));
        film.setLanguage(language);
        film.setLastUpdate(new Date());
        filmRepository.save(film);
    }



    @Override
    @Transactional
    public Boolean deleteFilm(int id) {
        System.out.println("Attempting to delete film with id: " + id);
        if (!filmRepository.existsById(id)) {
            return false;
        }
        List<Inventory> inventories = inventoryRepository.findByFilm_FilmId(id);
        for (Inventory inventory : inventories) {
            List<Rental> rentals = rentalRepository.findByInventory(inventory);
            rentalRepository.deleteAll(rentals);
        }
        inventoryRepository.deleteAll(inventories);
        filmRepository.deleteById(id);
        return true;
    }
    @Override
    public List<Language> getAllLanguages() {
        return (List<Language>) languageRepository.findAll();
    }
}

