package com.example.filmorate.controller;

import com.example.filmorate.exceptions.ValidationException;
import com.example.filmorate.model.Film;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;

public class FilmControllerTest {
    FilmController filmController;
    Film film;

    @BeforeEach
    void setUp(){
        filmController = new FilmController();
        film = new Film();
        film.setId(1);
        film.setName("arthouse movie Twilight");
        film.setDescription("It's a skin of a killer,Bella");
        film.setDuration(90);
        LocalDate date = LocalDate.of(2008,1,1);
        film.setReleaseDate(date);
    }

    @Test
    void shouldCreateFilm(){
        int expectedSize=0;
         filmController.newFilm(film);
        Assertions.assertEquals(expectedSize, filmController.getFilms().size());
    }

    @Test
    void shouldThrowExceptionWhenNameNull(){
        film.setName(null);
        String expectedMessage = "Неверное заполнение поля";

        Executable executable = () -> filmController.newFilm(film);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedMessage,ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameBlank(){
        film.setName("");
        String expectedMessage = "Неверное заполнение поля";

        Executable executable = () -> filmController.newFilm(film);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedMessage,ex.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenDescriptionTooLong(){
        film.setDescription("Bella".repeat(100));
        String expectedMessage = "Неверное заполнение поля";

        Executable executable = () -> filmController.newFilm(film);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedMessage,ex.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenDurationLessThanZero(){
        film.setDuration(-90);
        String expectedMessage = "Неверное заполнение поля";

        Executable executable = () -> filmController.newFilm(film);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedMessage,ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenFilmTooOld(){
        LocalDate date = LocalDate.of(1880,1,1);
        film.setReleaseDate(date);
        String expectedMessage = "Неверное заполнение поля";

        Executable executable = () -> filmController.newFilm(film);
        ValidationException ex = Assertions.assertThrows(ValidationException.class,executable);
        Assertions.assertEquals(expectedMessage,ex.getMessage());
    }
}
