package com.geekbrains.service;

import com.geekbrains.controllers.dto.UserDto;
import com.geekbrains.controllers.dto.UserType;
import com.geekbrains.entities.Role;
import com.geekbrains.entities.User;
import com.geekbrains.exceptions.ManagerIsEarlierThanNeedException;
import com.geekbrains.exceptions.UnknownUserTypeException;
import com.geekbrains.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;
    public final RoleService roleService;

    public UserService(UserRepository userRepository, RoleService roleService){
        this.userRepository=userRepository;
        this.roleService = roleService;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getAllUsersWithType(UserType userType) {
        return userRepository.findAllByroles(userType);
    }

    public User saveUser(UserDto userDto){
        if (userDto.getUserType().equals(UserType.MANAGER)){
            saveManager(userDto);
        }else if (userDto.getUserType().equals(UserType.USER)){
            saveTypicallyUser(userDto);
        }
        throw new UnknownUserTypeException();
    }

    private User saveTypicallyUser(UserDto userDto) {
        User user=createUserFromDto(userDto);
        Role role = roleService.getByName("ROLE_USER");
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    private User saveManager(UserDto userDto) {
        if (userDto.getAge()>18){
            User user =createUserFromDto(userDto);
            Role role = roleService.getByName("ROLE_MANAGER");
            user.setRoles(List.of(role));
            return userRepository.save(user);
        }
        throw  new ManagerIsEarlierThanNeedException("Пользователь младше 18 лет");
    }

    private User createUserFromDto(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setPhone(userDto.getPhone());
        user.setPassword(userDto.getPassword());
        return user;
    }

}
