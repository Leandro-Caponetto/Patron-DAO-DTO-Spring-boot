package com.leandro.service.interfaces;

import com.leandro.presentation.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> findAll();
    UserDto findById(long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Long id);
    String deleteUser(Long id);
}
