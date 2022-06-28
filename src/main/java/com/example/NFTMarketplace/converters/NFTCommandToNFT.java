package com.example.NFTMarketplace.converters;

import com.example.NFTMarketplace.commands.NFTCommand;
import com.example.NFTMarketplace.model.Bundle;
import com.example.NFTMarketplace.model.NFT;
import com.example.NFTMarketplace.model.User;
import com.example.NFTMarketplace.repositiories.BundleRepository;
import com.example.NFTMarketplace.repositiories.UserRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NFTCommandToNFT implements Converter<NFTCommand, NFT> {

    private BundleRepository bundleRepository;
    private UserRepository userRepository;

    public NFTCommandToNFT(BundleRepository bundleRepository, UserRepository userRepository) {
        this.bundleRepository = bundleRepository;
        this.userRepository = userRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public NFT convert(NFTCommand source) {
        if (source == null) {
            return null;
        }

        final NFT nft = new NFT();
        nft.setId(source.getId());
        nft.setName(source.getName());
        nft.setContractAddress(source.getContractAddress());
        nft.setImgUrl(source.getImgUrl());
        nft.setPrice(source.getPrice());

        Optional<Bundle> bundle = bundleRepository.findById(source.getBundleId());

        if (bundle.isPresent()) {
            nft.setBundle(bundle.get());
        } else {
            nft.setBundle(bundleRepository.getBundleByName("Unknown").get());
        }

        Optional<User> user = userRepository.findById(source.getUserId());

        if (user.isPresent()) {
            nft.setUser(user.get());
        } else {
            nft.setUser(userRepository.getUserByName("Unknown").get());
        }

        return nft;
    }
}
