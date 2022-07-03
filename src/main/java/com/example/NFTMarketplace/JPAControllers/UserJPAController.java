package com.example.NFTMarketplace.JPAControllers;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.API.model.UserDTO;
import com.example.NFTMarketplace.model.User;
import com.example.NFTMarketplace.services.CurrencyService;
import com.example.NFTMarketplace.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserJPAController {

    private final UserService userService;

    public UserJPAController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getAllCurrencies(){
        return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return new ResponseEntity<UserDTO>(userService.getUserById(id),HttpStatus.OK);
    }

    @GetMapping("nickname/{nickname}")
    public ResponseEntity<UserDTO> getUserByNickname(@PathVariable String nickname){
        return new ResponseEntity<UserDTO>(userService.getUserByNickname(nickname), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<UserDTO>(userService.createNewUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return new ResponseEntity<UserDTO>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
}
