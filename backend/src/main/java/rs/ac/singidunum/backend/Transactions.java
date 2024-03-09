package rs.ac.singidunum.backend;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name = "buyer", referencedColumnName = "userId")
    private Users buyer;

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "bikeId")
    private Bikes product;

    @Column(name = "datetime", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime datetime;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Users getBuyer() {
        return buyer;
    }

    public void setBuyer(Users buyer) {
        this.buyer = buyer;
    }

    public Bikes getProduct() {
        return product;
    }

    public void setProduct(Bikes product) {
        this.product = product;
    }
}
