package com.example.NFTMarketplace.controllers;

import com.example.NFTMarketplace.commands.UserCommand;
import com.example.NFTMarketplace.converters.UserCommandToUser;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.model.User;
import com.example.NFTMarketplace.repositiories.NFTRepository;
import com.example.NFTMarketplace.repositiories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

    private UserRepository userRepository;
    private NFTRepository nftRepository;
    private UserCommandToUser userCommandToUser;

    public UserController(UserRepository userRepository, NFTRepository nftRepository, UserCommandToUser userCommandToUser) {
        this.userRepository = userRepository;
        this.nftRepository = nftRepository;
        this.userCommandToUser = userCommandToUser;
    }

    @RequestMapping(value = {"/users", "/user/list"})
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user/list";
    }

    @RequestMapping("/user/{id}/show")
    public String getUserDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "user/show";
    }

    @RequestMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping
    @RequestMapping("/user/new")
    public String newUser(Model model){
        model.addAttribute("user", new UserCommand());
        return "user/addedit";
    }

    @GetMapping
    @RequestMapping("/user/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userRepository.findById(id).get());
        return "user/addedit";
    }

    @PostMapping("user")
    public String saveOrUpdate(@ModelAttribute UserCommand command){

        Optional<User> userOptional = Optional.ofNullable(userRepository.getUserById(command.getId()));

        if (!userOptional.isPresent()) {
            User detachedArtist = userCommandToUser.convert(command);
            userRepository.save(detachedArtist);
            return "redirect:/users";
        } else {
            User editedUser  = userRepository.findById(command.getId()).get();
            editedUser.setId(command.getId());
            editedUser.setName(command.getName());
            editedUser.setSurname(command.getSurname());
            editedUser.setNickname(command.getNickname());
            userRepository.save(editedUser);
            return "redirect:/users";
        }
    }
}
