package com.example.filmorate.controller;

import com.example.filmorate.exceptions.NotFoundException;
import com.example.filmorate.exceptions.ValidationException;
import com.example.filmorate.model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class UserController {


    Map<Integer,User> users = new HashMap<>();
    int count=1;

    @GetMapping("/users")
    public Collection<User> getUsers(){
        return users.values();
    }

    @PostMapping("/users")
    public User newUser(@RequestBody User user){
        if(!user.getEmail().contains("@") || user.getLogin().isBlank() || user.getLogin()==null ||
        user.getLogin().matches("^(?!.*\\s).+$\n") || user.getBirthday().isAfter(LocalDate.now())){
            throw new ValidationException("Поля не соответствуют условиям");
        }
        if(user.getName()==null){
            user.setName(user.getLogin());
        }
        user.setId(count++);
            users.put(user.getId(),user);
        return user;
    }

    @PutMapping("/users")
    public User putUser(@RequestBody User user){
        if (!users.containsKey(user.getId())) {
            throw new NotFoundException("id не существует");
        }
        users.put(user.getId(), user);
        return user;
    }
}
