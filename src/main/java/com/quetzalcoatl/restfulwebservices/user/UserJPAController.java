package com.quetzalcoatl.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAController {

    private UserRepository repository;

    public UserJPAController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException ("id=" + id);
        }
        return user.get();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User created = repository.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
