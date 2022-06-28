package com.example.NFTMarketplace.repositiories;

import com.example.NFTMarketplace.model.Bundle;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BundleRepository extends CrudRepository<Bundle, Long> {
    Optional<Bundle> getBundleByName(String name);
    Bundle getBundleById(Long id);
}

