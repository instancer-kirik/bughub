package com.instance.bughub.controllers;

import com.instance.bughub.entities.User;
import com.instance.bughub.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {
    @Autowired
    private final UserRepo repo;
    @Autowired
    public UserController(UserRepo repo) {
        this.repo = repo;
    }
    //@GetMapping("/")
    @GetMapping
    public String test(){
        return "test";
    }
    @PostMapping(value = "/addUser",consumes = {"application/json"})
    public ResponseEntity<?> addUser(@RequestBody User user){
        repo.save(user);
        System.out.println("called user post map");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(value = "/allUsers")
    public  ResponseEntity<?> getAllUsers(){
        List<User> s;
        s=repo.findAll();
        System.out.println("called user get all");
        return new ResponseEntity<>(s,HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        Optional<User> userOptional = repo.findById(id);

        if (userOptional.isPresent()) {
            repo.deleteById(id);
            System.out.println("User with id " + id + " deleted");
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}

