package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.singidunum.backend.util.JwtUtil;

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
    public User addUser(@RequestBody User user){
        return service.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return service.getUsers();
    }

    @GetMapping("/user/id/{id}")
    public User findUserById(@PathVariable int id){
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
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/id/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteUser(id);
    }
}
