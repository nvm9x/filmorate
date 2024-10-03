 package com.example.filmorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class Film {
    @NonNull
    private int id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private int duration;
}
