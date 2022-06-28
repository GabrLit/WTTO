package com.example.NFTMarketplace.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class NFT {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String contractAddress;
    private String imgUrl;
    private String price;

    @ManyToOne
    private Bundle bundle;

    @ManyToOne
    private User user;

    public NFT(String name, String contractAddress, String imgUrl, String price, Bundle bundle, User user) {
        this.name = name;
        this.contractAddress = contractAddress;
        this.imgUrl = imgUrl;
        this.price = price;
        this.bundle = bundle;
        this.user = user;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }
}
