package com.example.filmorate.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class User {
    @NonNull
    private int id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
}
