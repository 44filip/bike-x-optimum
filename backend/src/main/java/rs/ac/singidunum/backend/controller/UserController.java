package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;
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
    public User findUserByEmail(@PathVariable String email){
        return service.getUserByEmail(email);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        return service.deleteUser(id);
    }

}
