package rs.ac.singidunum.backend;

import jakarta.persistence.*;

@Entity
@Table(name = "BIKES")
public class Bikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bikeId;
    private String model;
    private double price;
    private String imgRelativePath;

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgRelativePath() {
        return imgRelativePath;
    }

    public void setImgRelativePath(String imgRelativePath) {
        this.imgRelativePath = imgRelativePath;
    }
}
