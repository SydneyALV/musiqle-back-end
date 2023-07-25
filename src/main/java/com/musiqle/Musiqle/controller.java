package com.musiqle.Musiqle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping("/welcome")
    public String first_api() {
        
        return "Welcome";
    }
    @GetMapping("/")
    public String homepage() {
        
        return "HomePage";
    }
}
