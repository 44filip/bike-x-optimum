package rs.ac.singidunum.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private double balance;
    private String role;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;  // transient field for JWT access token

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String refreshToken; // transient field for JWT refresh token
}