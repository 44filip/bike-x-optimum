package rs.ac.singidunum.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BIKES")
public class Bike {

    @Id
    @GeneratedValue
    private int bikeId;
    private String model;
    private double price;
    private String imgPath;
}
