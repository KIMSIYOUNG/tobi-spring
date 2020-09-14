package com.example.tobibookspring;

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
