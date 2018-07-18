package ru.itis.rabbitproducer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rabbitproducer.dto.UserDTO;
import ru.itis.rabbitproducer.services.UserService;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @CrossOrigin(origins = "http://localhost:8083")
    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO){

        this.userService.Send(userDTO);

        return ResponseEntity.ok("{\"status\" : \"Success\"}");

    }
}
