package com.example.tobibookspring.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class User {
    String id;
    String name;
    String password;

    public User() {
    }
}
