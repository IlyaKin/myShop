package com.geekbrains.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
