package com.quetzalcoatl.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService userDaoService) {
        service = userDaoService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException ("id=" + id);
        }
        return user;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User created = service.save(user);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if(user==null){
            throw new UserNotFoundException("id-"+ id);
        }
        return ResponseEntity.noContent().build();
    }
}
