package me.healthcare;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
public class BloodPressureController {

 //   @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
}
