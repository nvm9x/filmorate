package com.example.filmorate.controller;

import com.example.filmorate.exceptions.NotFoundException;
import com.example.filmorate.exceptions.ValidationException;
import com.example.filmorate.model.Film;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class FilmController {
    Map<Integer,Film> films = new HashMap<>();
    int count=1;

    @GetMapping("/films")
    public Collection<Film> getFilms() {
        return films.values();
    }

    @PostMapping("/films")
    public Film newFilm(@RequestBody Film film) {
        LocalDate minDate = LocalDate.of(1895,12,28);
        if(film.getName()==null || film.getName().isBlank() || film.getDescription().length()>200 ||
                film.getReleaseDate().isBefore(minDate) || film.getDuration()<0){
            throw new ValidationException("Неверное заполнение поля");
        }
        film.setId(count++);
        films.put(film.getId(),film);
        return film;
    }

    @PutMapping("/films")
    public Film putFilm(@RequestBody Film film) {
        if(!films.containsKey(film.getId())){
            throw new NotFoundException("id не существует");
        }

        films.put(film.getId(),film);
        return film;
    }
}
