package com.example.NFTMarketplace.controllers;

import com.example.NFTMarketplace.commands.NFTCommand;
import com.example.NFTMarketplace.converters.NFTCommandToNFT;
import com.example.NFTMarketplace.model.Bundle;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.model.NFT;
import com.example.NFTMarketplace.repositiories.BundleRepository;
import com.example.NFTMarketplace.repositiories.NFTRepository;
import com.example.NFTMarketplace.repositiories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class NFTController {

    private NFTRepository nftRepository;
    private NFTCommandToNFT nftCommandToNFT;
    private BundleRepository bundleRepository;
    private UserRepository userRepository;

    public NFTController(NFTRepository nftRepository, NFTCommandToNFT nftCommandToNFT, BundleRepository bundleRepository, UserRepository userRepository) {
        this.nftRepository = nftRepository;
        this.nftCommandToNFT = nftCommandToNFT;
        this.bundleRepository = bundleRepository;
        this.userRepository = userRepository ;
    }

    @GetMapping
    @RequestMapping(value = {"/nfts" , "nft/list"})
    public String getNFTs(Model model) {
        model.addAttribute("nfts", nftRepository.findAll());
        return "nft/list";
    }

    @GetMapping
    @RequestMapping("/nft/{id}/show")
    public String getNFTDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("nft", nftRepository.findById(id).get());
        return "nft/show";
    }

    @GetMapping
    @RequestMapping("/nft/{id}/delete")
    public String deleteNFT(@PathVariable("id") Long id) {
        nftRepository.deleteById(id);
        return "redirect:/nfts";
    }

    @GetMapping
    @RequestMapping("/nft/new")
    public String newNFT(Model model){
        model.addAttribute("nft", new NFTCommand());
        model.addAttribute("bundles", bundleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "nft/addedit";
    }

    @GetMapping
    @RequestMapping("/nft/{id}/edit")
    public String editNFT(Model model, @PathVariable("id") Long id){
        NFT foundRepository =  nftRepository.findById(id).get();
        NFTCommand newCommand = new NFTCommand();
        newCommand.setId(foundRepository.getId());
        newCommand.setName(foundRepository.getName());
        newCommand.setContractAddress(foundRepository.getContractAddress());
        newCommand.setImgUrl(foundRepository.getImgUrl());
        newCommand.setPrice(foundRepository.getPrice());
        model.addAttribute("nft", newCommand);
        model.addAttribute("bundles", bundleRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "nft/addedit";
    }

    @PostMapping("nft")
    public String saveOrUpdate(@ModelAttribute NFTCommand command){

        Optional<NFT> nftOptional = nftRepository.getNFTById(command.getId());

        if (!nftOptional.isPresent()) {
            NFT detachedNFT = nftCommandToNFT.convert(command);
            nftRepository.save(detachedNFT);
            return "redirect:/nfts/";
        } else {
            NFT editedNFT  = nftRepository.findById(command.getId()).get();
            editedNFT.setId(command.getId());
            editedNFT.setName(command.getName());
            editedNFT.setContractAddress(command.getContractAddress());
            editedNFT.setImgUrl(command.getImgUrl());
            editedNFT.setPrice(command.getPrice());
            editedNFT.setBundle(bundleRepository.getBundleById(command.getBundleId()));
            editedNFT.setUser(userRepository.getUserById(command.getUserId()));
            nftRepository.save(editedNFT);
            return "redirect:/nfts/";
        }

    }
}
