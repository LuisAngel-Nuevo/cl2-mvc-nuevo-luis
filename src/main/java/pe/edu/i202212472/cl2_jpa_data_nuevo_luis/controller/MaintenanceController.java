package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmDetailDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.dto.FilmInsertDTO;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Language;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.service.MaintenanceService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    MaintenanceService maintenanceService;

    @GetMapping("/start")
    public String start(Model model) {

        List<FilmDTO> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetailDTO filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("film", filmDetailDto);
        return "maintenance_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        FilmDetailDTO filmDetailDto = maintenanceService.findFilmById(id);
        List<Language> languages = maintenanceService.getAllLanguages();

        List<String> specialFeaturesList = Arrays.asList(filmDetailDto.specialFeatures().split(",\\s*"));
        model.addAttribute("film", filmDetailDto);
        model.addAttribute("languages", languages);
        model.addAttribute("specialFeaturesList", specialFeaturesList);
        return "maintenance_edit";
    }

    @PostMapping("/edit-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDTO filmDetailDTO, Model model) {
        maintenanceService.updateFilm(filmDetailDTO);
        return "redirect:/maintenance/start";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        FilmInsertDTO filmInsertDto = new FilmInsertDTO(
                "",
                "",
                null,
                null,
                "",
                0,
                0.0,
                null,
                0.0,
                "",
                "",
                new Date()
        );

        List<Language> languages = maintenanceService.getAllLanguages();

        model.addAttribute("filmInsertDto", filmInsertDto);
        model.addAttribute("languages", languages);

        return "maintenance_register";

    }
    @PostMapping("/register")
    public String insertFilm (FilmInsertDTO filmInsertDTO){
        maintenanceService.registerFilm(filmInsertDTO);
        return "redirect:/maintenance/start";
    }


    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // Llama al servicio para eliminar la película
        Boolean isDeleted = maintenanceService.deleteFilm(id);

        // Verifica si la eliminación fue exitosa
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("message", "Film deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No associated inventory found. Film not deleted.");
        }

        return "redirect:/maintenance/start";
    }
}
