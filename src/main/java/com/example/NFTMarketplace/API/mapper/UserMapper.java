package com.example.NFTMarketplace.API.mapper;

import com.example.NFTMarketplace.API.model.UserDTO;
import com.example.NFTMarketplace.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}