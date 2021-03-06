package com.geekbrains.controllers;


import com.geekbrains.controllers.dto.UserDto;
import com.geekbrains.controllers.dto.UserType;
import com.geekbrains.entities.User;
import com.geekbrains.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("shop/v1/user")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping
    public List<User> getAllUsers(@RequestParam(value = "type", required = false) UserType type) {
        if (type != null) {
            return userService.getAllUsersWithType(type);
        } else return userService.getAllUsers();
    }
}
