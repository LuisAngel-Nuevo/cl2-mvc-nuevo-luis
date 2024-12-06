package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.service;

import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDetailDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmEditDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmInsertDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Language;

import java.util.List;

public interface MaintenanceService {


    List<FilmDTO> findAllFilms();
    FilmDetailDTO findFilmById(int id);
    Boolean updateFilm(FilmDetailDTO filmDetailDto);
    void registerFilm(FilmInsertDTO filmInsertDto);
    Boolean deleteFilm(int id);
    List<Language> getAllLanguages();}
