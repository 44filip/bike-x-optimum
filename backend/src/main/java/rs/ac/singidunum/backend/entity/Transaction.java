package rs.ac.singidunum.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Transient
    @JsonProperty("userId")
    private int userId;

    @Transient
    @JsonProperty("bikeId")
    private int bikeId;

    @ManyToOne
    @JoinColumn(name = "buyer", referencedColumnName = "userId")
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "bikeId")
    private Bike product;

    @Column(name = "datetime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime datetime;

    @PrePersist
    public void prePersist() {
        this.datetime = LocalDateTime.now();
    }
}