package com.endava.proiectfinalandreea.controller;


import com.endava.proiectfinalandreea.model.*;
import com.endava.proiectfinalandreea.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable(name = "userId") Integer userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/admin")
    public UserDto createAndminUser(@RequestBody UserAdminDtoCreateRequest userAdminDtoCreateRequest) {
        return userService.createAdminUser(userAdminDtoCreateRequest);
    }

    @PostMapping("/individual-client")
    public UserDto createIndividualClientUser(@RequestBody UserIndividualClientDtoCreateRequest userIndividualClientDtoCreateRequest) {
        return userService.createIndividualClientUser(userIndividualClientDtoCreateRequest);
    }

    @PostMapping("/company-client")
    public UserDto createIndividualClientUser(@RequestBody UserCompanyClientDtoCreateRequest userCompanyClientDtoCreateRequest) {
        return userService.createCompanyClientUser(userCompanyClientDtoCreateRequest);
    }

    @PostMapping("/supplier")
    public UserDto createSupplierUser(@RequestBody UserSupplierDtoCreateRequest userSupplierDtoCreateRequest) {
        return userService.createSupplierClientUser(userSupplierDtoCreateRequest);
    }

    @DeleteMapping("/{userId}")
    public void deletePost(@PathVariable(name = "userId") Integer userId) {
        userService.deleteUser(userId);
    }

}