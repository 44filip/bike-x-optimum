package rs.ac.singidunum.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @SuppressWarnings("null")
    public User saveUser(User user) {
        return repository.save(user);
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User getUserById(int id) {
        return repository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String deleteUser(int id) {
        repository.deleteById(id);
        return "user " + id + " removed";
    }

    public User updateUser(User user) {
        User existingUser = repository.findById(user.getUserId()).orElse(null);
        assert existingUser != null;
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setBalance(user.getBalance());
        existingUser.setPassword(user.getPassword());
        return repository.save(existingUser);
    }


}
