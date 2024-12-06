package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Inventory;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findByFilm_FilmId(int filmId);
}
