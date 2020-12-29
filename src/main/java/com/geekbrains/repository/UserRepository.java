package com.geekbrains.repository;

import com.geekbrains.entities.Role;
import com.geekbrains.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll ();;

    Optional<User> findByPhone(String phone);

    List<User> findAllByRoles(Role role);
}
