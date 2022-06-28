package com.example.NFTMarketplace.tools;

import com.example.NFTMarketplace.model.Bundle;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.model.NFT;
import com.example.NFTMarketplace.model.User;
import com.example.NFTMarketplace.repositiories.BundleRepository;
import com.example.NFTMarketplace.repositiories.CurrencyRepository;
import com.example.NFTMarketplace.repositiories.NFTRepository;
import com.example.NFTMarketplace.repositiories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    private NFTRepository nftRepository;
    private UserRepository userRepository;
    private BundleRepository bundleRepository;
    private CurrencyRepository currencyRepository;

    public DBInflater(NFTRepository nftRepository, UserRepository userRepository, BundleRepository bundleRepository, CurrencyRepository currencyRepository) {
        this.nftRepository = nftRepository;
        this.userRepository = userRepository;
        this.bundleRepository = bundleRepository;
        this.currencyRepository= currencyRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {

        User mark = new User("Mark", "Ruffalo", "MRuf");
        Bundle boredApe = new Bundle("Bored Ape Yacht Club", "0x86d8AB032e6eb7190c2547421c2e702f2e9a3692" );
        NFT boredApe4260 = new NFT("Bored Ape #4260", "0x63b0Db631e09346AcC7DecBb5C563e63bb10FB06", "https://img.seadn.io/files/183b82b682d2f6adde67c591d72cfc37.png",
                "75", boredApe, mark);
        Currency ethereum = new Currency("Ethereum", "ETH");
        currencyRepository.save(ethereum);
        bundleRepository.save(boredApe);
        userRepository.save(mark);
        nftRepository.save(boredApe4260);


        User casper = new User("Casper", "Duff", "Caduff0x");
        Bundle greatGoat = new Bundle("Great Goat Club", "0x271b97fcBd218FE66f69d4478Cd4E161eaA7D10F");
        NFT greatGoat8265= new NFT("Great Goat #8265", "1BWutmTvYPwDtmw9abTkS4Ssr8no61spGAvW1X6NDix", "https://img.seadn.io/files/ca65107f05f436e447f13d5ec7120f55.png",
                "0.1871", greatGoat, casper);
        Currency binance = new Currency("Binance", "BNB");
        currencyRepository.save(binance);
        bundleRepository.save(greatGoat);
        userRepository.save(casper);
        nftRepository.save(greatGoat8265);



        User bruce = new User("Bruce", "Tin", "BruTinqx");
        Bundle cryptoPunks = new Bundle("Crypto Punks", "0x271b97fcBd218FE66f69d4478Cd4E161eaA7D10F");
        NFT cryptoPunk2438 = new NFT("CryptoPunk #2438", "0x0d4428C8E62e894999d8E4c1845f23cb5F9d919d", "https://img.seadn.io/files/1404f372107c7f6049b4b38929b422a9.png",
                "48.667", cryptoPunks, bruce);
        Currency avalanche = new Currency("Avalanche", "AVAX");
        currencyRepository.save(avalanche);
        bundleRepository.save(cryptoPunks);
        userRepository.save(bruce);
        nftRepository.save(cryptoPunk2438);
    }
}
