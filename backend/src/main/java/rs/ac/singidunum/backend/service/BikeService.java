package rs.ac.singidunum.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.backend.entity.Bike;
import rs.ac.singidunum.backend.repository.BikeRepository;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository repository;

    public List<Bike> getBikes() {
        return repository.findAll();
    }

    public Bike getBikeById(int id) {
        return repository.findById(id).orElse(null);
    }

    public Bike getBikeByModel(String model) {
        return repository.findByModel(model);
    }
}