package com.endava.proiectfinalandreea.controller;


import com.endava.proiectfinalandreea.model.UserDto;
import com.endava.proiectfinalandreea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/supplier")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDto> getSuppliers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable(name = "userId") Integer userId) {
        return userService.getUser(userId);
    }
    @PostMapping()
    public UserDto createSupplier(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deletePost(@PathVariable(name = "userId") Integer userId){
        userService.deleteUser(userId);
    }

}