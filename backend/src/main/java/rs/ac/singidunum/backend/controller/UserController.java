package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.service.UserService;
import rs.ac.singidunum.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

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
        return service.getUserByEmail(email);
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
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        User user = service.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String hashedPassword = DigestUtils.sha256Hex(password.trim());

        if (!user.getPassword().equals(hashedPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        String accessToken = jwtUtil.generateToken(user.getEmail(), user.getRole(), 15 * 60 * 1000); // 15 min
        String refreshToken = jwtUtil.generateToken(user.getEmail(), user.getRole(), 7 * 24 * 60 * 60 * 1000); // 7 days

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        // Remove password before sending user data
        user.setPassword(null);

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("tokens", tokens);

        return ResponseEntity.ok(response);
    }
}