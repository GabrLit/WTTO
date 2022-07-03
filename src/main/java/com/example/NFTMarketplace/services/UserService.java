package com.example.NFTMarketplace.services;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.API.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO getUserByNickname(String nickname);

    UserDTO createNewUser(UserDTO userDTO);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUserById(Long id);
}
