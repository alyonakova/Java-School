package ru.t_systems.alyona.sbb.tickets.e2e.selenium.fixtures;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    String login;

    String password;

    public static User existingUserWithWrongPassword() {
        return User.builder()
                .login("zmiller")
                .password("unknown")
                .build();
    }

    public static User existingUserWithCorrectPassword() {
        return User.builder()
                .login("zmiller")
                .password("123456")
                .build();
    }

    public static User nonExistingUser() {
        return User.builder()
                .login("unknown")
                .password("unknown")
                .build();
    }
}
