package com.example.NFTMarketplace.converters;

import com.example.NFTMarketplace.commands.BundleCommand;
import com.example.NFTMarketplace.commands.CurrencyCommand;
import com.example.NFTMarketplace.model.Bundle;
import com.example.NFTMarketplace.model.Currency;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CurrencyCommandToCurrency implements Converter<CurrencyCommand, Currency> {

    @Synchronized
    @Nullable
    @Override
    public Currency convert(CurrencyCommand source) {
        if (source == null) {
            return null;
        }

        final Currency currency = new Currency();
        currency.setName(source.getName());
        currency.setSymbol(source.getSymbol());

        return currency;
    }
}
