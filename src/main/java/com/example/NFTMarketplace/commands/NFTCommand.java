package com.example.NFTMarketplace.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NFTCommand {
    private Long id;
    private String name;
    private String contractAddress;
    private String imgUrl;
    private String price;
    private Long bundleId;
    private Long userId;
}
