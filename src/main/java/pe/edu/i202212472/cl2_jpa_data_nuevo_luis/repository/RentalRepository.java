package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Inventory;
import pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity.Rental;

import java.util.List;

public interface RentalRepository extends CrudRepository<Rental, Integer> {
    List<Rental> findByInventory(Inventory inventory);
}
