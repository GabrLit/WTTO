package com.example.NFTMarketplace.API.mapper;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.model.Currency;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    CurrencyDTO currencyToCurrencyDTO(Currency currency);
    Currency currencyDTOToCurrency(CurrencyDTO currencyDTO);
}
