package com.example.NFTMarketplace.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyCommand {
    private Long id;
    private String name;
    private String symbol;
}
