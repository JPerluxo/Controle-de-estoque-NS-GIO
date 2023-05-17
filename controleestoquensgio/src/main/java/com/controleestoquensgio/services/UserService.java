package com.controleestoquensgio.services;

import com.controleestoquensgio.dtos.UserDto;
import com.controleestoquensgio.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
