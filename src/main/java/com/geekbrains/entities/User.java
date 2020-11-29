package com.geekbrains.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class  User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;

    @NumberFormat
    private String phone;

    private String email;
    private Integer age;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "users_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
