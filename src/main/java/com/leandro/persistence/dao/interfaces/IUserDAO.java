package com.leandro.persistence.dao.interfaces;

import com.leandro.persistence.UserEntity;
import com.leandro.presentation.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);
    void  saveUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
}
