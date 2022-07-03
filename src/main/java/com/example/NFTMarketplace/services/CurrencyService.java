package com.example.NFTMarketplace.services;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDTO> getAllCurrencies();
    CurrencyDTO getCurrencyById(Long id);

    CurrencyDTO getCurrencyBySymbol(String symbol);

    CurrencyDTO createNewCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO);

    void deleteCurrencyById(Long id);
}
