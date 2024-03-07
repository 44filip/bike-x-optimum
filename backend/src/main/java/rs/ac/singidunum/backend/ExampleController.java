package rs.ac.singidunum.backend;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/example")
public class ExampleController {
    @GetMapping
    public HashMap<String, Object> getExampleResponse(){
        HashMap<String, Object> json = new HashMap<>();
        json.put("message", "Hello from Spring Boot");
        json.put("timestamp", LocalDateTime.now());
        json.put("user", "b017");
        return json;
    }
}
