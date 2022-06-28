package com.example.NFTMarketplace.JPARepositiories;

import com.example.NFTMarketplace.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyJPARepository extends JpaRepository<Currency, Long> {
}
