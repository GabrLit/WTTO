package com.example.NFTMarketplace.services;

import com.example.NFTMarketplace.API.mapper.CurrencyMapper;
import com.example.NFTMarketplace.API.mapper.UserMapper;
import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.API.model.UserDTO;
import com.example.NFTMarketplace.JPARepositiories.CurrencyJPARepository;
import com.example.NFTMarketplace.JPARepositiories.UserJPARepository;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserJPARepository userJPARepository;
    UserMapper userMapper;

    public UserServiceImpl(UserJPARepository userJPARepository, UserMapper userMapper) {
        this.userJPARepository = userJPARepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userJPARepository.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.userToUserDTO(userJPARepository.findById(id).get());
    }

    @Override
    public UserDTO getUserByNickname(String nickname) {
        return userMapper.userToUserDTO(userJPARepository.getByNickname(nickname));
    }

    @Override
    public UserDTO createNewUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userJPARepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user= userMapper.userDTOToUser(userDTO);
        user.setId(id);

        User savedUser = userJPARepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userJPARepository.deleteById(id);
        return;
    }
}
