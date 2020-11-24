package com.geekbrains.controllers.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    private String firstName;
    private String lastName;
    @NotNull(message = "Поле Пароль не может быть пустым")
    private String password;
    @NotNull(message = "Поле Телефон не может быть пустым")
    private String phone;
    private String email;
    @NotNull(message = "Возраст пользователя не может быть пустым")
    private Integer age;
    @NotNull(message = "Не выбран тип пользователя")
    private UserType userType;

}
