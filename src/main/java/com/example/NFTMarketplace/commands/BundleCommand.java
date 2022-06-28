package com.example.NFTMarketplace.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BundleCommand {
    private Long id;
    private String name;
    private String address;
}
