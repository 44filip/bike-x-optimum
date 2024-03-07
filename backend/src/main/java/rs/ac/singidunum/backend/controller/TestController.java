package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String getHome(){
        return "Home";
    }

    @GetMapping("/bikes")
    public String getBikes(){
        return "Bikes";
    }

    @GetMapping("/user")
    public Person getPerson(){
        Person p = new Person();
        p.setName("Michael");
        p.setAge(40);
        return p;
    }
}

class Person{
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
