package rs.ac.singidunum.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bikes")
public class BikeController {

    @Autowired
    private BikeRepository bikeRepository;

    @GetMapping
    public List<Bikes> getAllBikes() {
        return bikeRepository.findAll();
    }
}