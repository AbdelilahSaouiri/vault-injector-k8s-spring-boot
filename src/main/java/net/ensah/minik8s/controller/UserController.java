package net.ensah.minik8s.controller;


import net.ensah.minik8s.dtos.UserRequestDto;
import net.ensah.minik8s.dtos.UserResponseDto;
import net.ensah.minik8s.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<UserResponseDto> getUserByEmail(@RequestParam String email) {
        return new ResponseEntity<>(service.getByEmail(email), HttpStatus.OK);
    }

    @PostMapping
   // @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto requestDto) {
        return new ResponseEntity<>(service.addUser(requestDto), HttpStatus.CREATED);
    }
}
