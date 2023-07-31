package com.musiqle.Musiqle;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService service;
///Get All Users
    @GetMapping("/user")
    public List<Users> find() {
        return service.find();
    }
///Get User by Id
    @GetMapping("/user/{id}")
    public Users showEntry(@PathVariable Long id) {
        // Users user = new Users();
        // System.out.printIn(user.get())
        return service.showEntry(id);
    }

///Post a new user
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Users create(@RequestBody Users users) {
        return service.create(users);
    }

    @PatchMapping("/user/{id}/score")
    public Users update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        return service.update(id, (int) payload.get("score"));
    }

    // @PatchMapping("/user/{id}/{var}")
    // public Users update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
    //     return service.update(id, (int) payload.get(read(@PathVariable String var)));
    // }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
