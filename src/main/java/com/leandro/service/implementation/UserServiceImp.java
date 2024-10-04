package com.leandro.service.implementation;

import com.leandro.persistence.UserEntity;
import com.leandro.persistence.dao.interfaces.IUserDAO;
import com.leandro.presentation.dto.UserDto;
import com.leandro.service.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements IUserService {
    private final IUserDAO userDAO;

    public UserServiceImp(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public List<UserDto> findAll() {

        ModelMapper modelMapper = new ModelMapper();

        return this.userDAO.findAll()
                .stream()
                .parallel()
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(long id) {

        Optional<UserEntity> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()){
            ModelMapper modelMapper = new ModelMapper();
            UserEntity currentUser = userEntity.get();
            return modelMapper.map(currentUser, UserDto.class);
        }else {
            return new UserDto();
        }

    }

    @Override
    public UserDto createUser(UserDto userDto) {

        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            this.userDAO.saveUser(userEntity);
            return userDto;
        }catch (Exception e){
            throw new UnsupportedOperationException("Error al guardar el usuario" + e.getMessage());
        }

    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()){
            UserEntity currentUserEntity = userEntity.get();
            currentUserEntity.setName(userDto.getName());
            currentUserEntity.setLasname(userDto.getLasname());
            currentUserEntity.setEmail(userDto.getEmail());
            currentUserEntity.setAge(userDto.getAge());

            this.userDAO.updateUser(currentUserEntity);

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentUserEntity, UserDto.class);
        }else {
            throw new IllegalArgumentException("El usuario no existe");
        }


    }

    @Override
    public String deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.userDAO.findById(id);
        if (userEntity.isPresent()){
            UserEntity currentUserEntity = userEntity.get();
            this.userDAO.deleteUser(currentUserEntity);
            return "Usuario con ID " + id + "ha sido eliminado";
        }
        return "El usuario con ID " + id + " no existe";


    }
}
