package com.example.TaskManagementSystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        String pwd = "PD@1234";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String p1 = encoder.encode(pwd);
        System.out.println(p1);
//$2a$10$Ohu0KUxtsfx7SvDV66s97OAoHCdfXgur8eYarASJLZX.tBpuxlBLex
    }
}
