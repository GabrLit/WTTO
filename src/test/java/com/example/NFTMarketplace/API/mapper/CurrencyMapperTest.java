package com.example.NFTMarketplace.API.mapper;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.model.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyMapperTest {

    CurrencyMapper currencyMapper = CurrencyMapper.INSTANCE;

    @Test
    void currencyToCurrencyDTO() {

        Currency currency = new Currency();
        currency.setId(1L);
        currency.setName("Ethereum");
        currency.setSymbol("ETH");

        CurrencyDTO currencyDTO = currencyMapper.currencyToCurrencyDTO(currency);

        assertEquals("Ethereum", currencyDTO.getName());
    }
}