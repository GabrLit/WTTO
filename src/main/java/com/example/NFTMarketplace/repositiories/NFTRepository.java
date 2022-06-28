package com.example.NFTMarketplace.repositiories;

import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.model.NFT;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface NFTRepository extends CrudRepository<NFT, Long> {
    Optional<NFT> getNFTById(Long id);
}
