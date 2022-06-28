package com.example.NFTMarketplace.controllers;

import com.example.NFTMarketplace.commands.CurrencyCommand;
import com.example.NFTMarketplace.converters.CurrencyCommandToCurrency;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.repositiories.CurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class CurrencyController {

    private CurrencyRepository currencyRepository;
    private CurrencyCommandToCurrency currencyCommandToCurrency;

    public CurrencyController(CurrencyRepository currencyRepository, CurrencyCommandToCurrency currencyCommandToCurrency) {
        this.currencyRepository = currencyRepository;
        this.currencyCommandToCurrency = currencyCommandToCurrency;
    }

    @RequestMapping(value = {"/currencies", "/currency/list"})
    public String getCurrencies(Model model) {
        model.addAttribute("currencies", currencyRepository.findAll());
        return "currency/list";
    }

    @RequestMapping("/currency/{id}/show")
    public String getCurrencyDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("currency", currencyRepository.findById(id).get());
        return "currency/show";
    }

    @RequestMapping("/currency/{id}/delete")
    public String deleteCurrency(@PathVariable("id") Long id) {
        currencyRepository.deleteById(id);
        return "redirect:/currencies";
    }

    @GetMapping
    @RequestMapping("/currency/new")
    public String newCurrency(Model model){
        model.addAttribute("currency", new CurrencyCommand());
        return "currency/addedit";
    }

    @GetMapping
    @RequestMapping("/currency/{id}/edit")
    public String editCurrency(Model model, @PathVariable("id") Long id) {
        model.addAttribute("currency", currencyRepository.findById(id).get());
        return "currency/addedit";
    }

    @PostMapping("currency")
    public String saveOrUpdate(@ModelAttribute CurrencyCommand command){

        Optional<Currency> currencyOptional = currencyRepository.getCurrencyById(command.getId());

        if (!currencyOptional.isPresent()) {
            Currency detachedCurrency = currencyCommandToCurrency.convert(command);
            currencyRepository.save(detachedCurrency);
            return "redirect:/currencies";
        } else {
            Currency editedCurrency  = currencyRepository.findById(command.getId()).get();
            editedCurrency.setId(command.getId());
            editedCurrency.setName(command.getName());
            editedCurrency.setSymbol(command.getSymbol());
            currencyRepository.save(editedCurrency);
            return "redirect:/currencies";
        }
    }
}
