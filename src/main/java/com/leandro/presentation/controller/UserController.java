package com.leandro.presentation.controller;

import com.leandro.presentation.dto.UserDto;
import com.leandro.service.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //FindAll
    @GetMapping("/find")
    public ResponseEntity<List<UserDto>> findAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }
    //FindById
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);

    }
    //Crear un usuario
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
    }

    //Update User
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Long id){
        return new ResponseEntity<>(this.userService.updateUser(userDto, id), HttpStatus.CREATED);
    }

    //Delete User
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(this.userService.deleteUser(id), HttpStatus.NO_CONTENT);
    }
}
