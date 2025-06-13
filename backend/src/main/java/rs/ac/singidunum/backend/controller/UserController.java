package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.service.UserService;
import rs.ac.singidunum.backend.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.List;

@RestController
public class UserController {

    private final UserService service;
    private final JwtUtil jwtUtil;

    public UserController(UserService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/user/id/{id}")
    public User findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/user/email/{email}")
    public User findUserByEmail(@PathVariable String email) {
        User user = service.getUserByEmail(email);
        if (user != null) {
            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            user.setToken(accessToken);
            user.setRefreshToken(refreshToken);
            System.out.println("AccessToken: " + accessToken);
            System.out.println("RefreshToken: " + refreshToken);
        }
        return user;
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/id/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String hashedPassword = loginRequest.get("password");
        User user = service.getUserByEmail(email);

        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        user.setToken(accessToken);
        user.setRefreshToken(refreshToken);
        user.setPassword(null);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");

        User user = service.getUserByEmail(email);
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

        boolean matches = user.getPassword().equals(password);
        return ResponseEntity.ok(matches);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody String refreshToken) {
        try {
            String email = jwtUtil.extractSubject(refreshToken);
            if (email == null || !jwtUtil.validateToken(refreshToken)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
            }
            User user = service.getUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            String newAccessToken = jwtUtil.generateAccessToken(email);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }
    }
}
