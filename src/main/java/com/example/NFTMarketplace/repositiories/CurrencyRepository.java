package com.example.NFTMarketplace.repositiories;
import com.example.NFTMarketplace.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Optional<Currency> getCurrencyById(Long id);
}
