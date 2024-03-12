package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.Bike;
import rs.ac.singidunum.backend.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class BikeController {
    @Autowired
    private BikeService service;
    @GetMapping("/bikes")
    public List<Bike> findAllBikes(){
        return service.getBikes();
    }
    @GetMapping("/bike/{id}")
    public Bike findBikeById(@PathVariable int id){
        return service.getBikeById(id);
    }
    @GetMapping("/bike/{model}")
    public Bike findBikeByModel(@PathVariable String model){
        return service.getBikeByModel(model);
    }
}