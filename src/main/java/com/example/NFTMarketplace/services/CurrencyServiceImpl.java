package com.example.NFTMarketplace.services;

import com.example.NFTMarketplace.API.mapper.CurrencyMapper;
import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.JPARepositiories.CurrencyJPARepository;
import com.example.NFTMarketplace.model.Currency;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    CurrencyJPARepository currencyJPARepository;
    CurrencyMapper currencyMapper;

    public CurrencyServiceImpl(CurrencyJPARepository currencyJPARepository, CurrencyMapper currencyMapper){
        this.currencyJPARepository = currencyJPARepository;
        this.currencyMapper = currencyMapper;
    }

    @Override
    public List<CurrencyDTO> getAllCurrencies() {
        return currencyJPARepository.findAll().stream().map(currencyMapper::currencyToCurrencyDTO).collect(Collectors.toList());
    }

    @Override
    public CurrencyDTO getCurrencyById(Long id) {
        return currencyMapper.currencyToCurrencyDTO(currencyJPARepository.findById(id).get());
    }

    @Override
    public CurrencyDTO getCurrencyBySymbol(String symbol) {
        return currencyMapper.currencyToCurrencyDTO(currencyJPARepository.getBySymbol(symbol));
    }

    @Override
    public CurrencyDTO createNewCurrency(CurrencyDTO currencyDTO) {
        Currency currency = currencyMapper.currencyDTOToCurrency(currencyDTO);
        Currency savedCurrency = currencyJPARepository.save(currency);
        return currencyMapper.currencyToCurrencyDTO(savedCurrency);
    }

    @Override
    public CurrencyDTO updateCurrency(Long id, CurrencyDTO currencyDTO) {
        Currency currency = currencyMapper.currencyDTOToCurrency(currencyDTO);
        currency.setId(id);

        Currency savedCurrency = currencyJPARepository.save(currency);
        return currencyMapper.currencyToCurrencyDTO(savedCurrency);
    }

    @Override
    public void deleteCurrencyById(Long id) {
        currencyJPARepository.deleteById(id);
        return;
    }
}
