package pe.edu.i202212472.cl2_jpa_data_nuevo_luis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;
    private Date rentalDate;
    private Integer customerId;
    private Date returnDate;
    private Integer staffId;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name= "inventory_id")
    private Inventory inventory;
}
