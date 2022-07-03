package com.example.NFTMarketplace.JPARepositiories;

import com.example.NFTMarketplace.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CurrencyJPARepository extends JpaRepository<Currency, Long> {

    Currency getBySymbol(String symbol);
}
