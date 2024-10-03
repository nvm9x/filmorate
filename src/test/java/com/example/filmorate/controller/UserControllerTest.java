package com.example.filmorate.controller;

import com.example.filmorate.exceptions.ValidationException;
import com.example.filmorate.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

public class UserControllerTest {

    UserController userController;
    User user;
    @FunctionalInterface
    public interface Executable extends org.junit.jupiter.api.function.Executable {
        void execute();
    }

    @BeforeEach
    void setUp(){
        userController = new UserController();
        user = new User();
        user.setId(1);
        user.setName("john doe");
        LocalDate birthday = LocalDate.of(1998, 11, 10);
        user.setBirthday(birthday);
        user.setEmail("johndoe@gmail.com");
        user.setLogin("john-doe");
    }

    @Test
    void shouldCreateUser(){
        int expectedSize=1;
        userController.newUser(user);
        int actualSize = userController.getUsers().size();
        Assertions.assertEquals(expectedSize,actualSize);
    }

    @Test
    void ShouldThrowExceptionWhenLoginNull(){ // actual: NullPointerException
        user.setLogin(null);
        int expectedSize=0;
        String expectedMessage = "Поля не соответствуют условиям";

        Executable executable = () -> userController.newUser(user);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedSize,userController.getUsers().size());
        Assertions.assertEquals(expectedMessage,ex.getMessage());


    }
    @Test
    void ShouldThrowExceptionWhenLoginBlank(){
        user.setLogin("");
        int expectedSize=0;
        String expectedMessage = "Поля не соответствуют условиям";

        Executable executable = () -> userController.newUser(user);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedSize,userController.getUsers().size());
        Assertions.assertEquals(expectedMessage,ex.getMessage());


    }

    @Test
    void shouldThrowExceptionWhenWrongEmail(){
        user.setEmail("johndoegmail.com");
        boolean expected = false;
        String expectedMessage = "Поля не соответствуют условиям";

        Executable executable = () -> userController.newUser(user);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);

        Assertions.assertEquals(expected,user.getEmail().contains("@"));
        Assertions.assertEquals(expectedMessage, ex.getMessage());


    }

    @Test
    void shouldThrowExceptionWhenUserFromFuture(){
        LocalDate birthday = LocalDate.of(2030, 11, 10);
        user.setBirthday(birthday);
        boolean expected = true;
        String expectedMessage = "Поля не соответствуют условиям";

        Executable executable = () -> userController.newUser(user);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expected,user.getBirthday().isAfter(LocalDate.now()));
        Assertions.assertEquals(expectedMessage, ex.getMessage());


    }

}
