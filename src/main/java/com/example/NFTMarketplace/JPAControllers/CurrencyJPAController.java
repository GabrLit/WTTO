package com.example.NFTMarketplace.JPAControllers;

import com.example.NFTMarketplace.API.model.CurrencyDTO;
import com.example.NFTMarketplace.model.Currency;
import com.example.NFTMarketplace.services.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/currency")
public class CurrencyJPAController {

    private final CurrencyService currencyService;

    public CurrencyJPAController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("")
    public ResponseEntity<List<CurrencyDTO>> getAllCurrencies(){
        return new ResponseEntity<List<CurrencyDTO>>(currencyService.getAllCurrencies(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CurrencyDTO> getCurrencyById(@PathVariable Long id){
        return new ResponseEntity<CurrencyDTO>(currencyService.getCurrencyById(id),HttpStatus.OK);
    }

    @GetMapping("symbol/{symbol}")
    public ResponseEntity<CurrencyDTO> getCurrencyBySymbol(@PathVariable String symbol){
        return new ResponseEntity<CurrencyDTO>(currencyService.getCurrencyBySymbol(symbol), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CurrencyDTO> createNewCurrency(@RequestBody CurrencyDTO currencyDTO){
        return new ResponseEntity<CurrencyDTO>(currencyService.createNewCurrency(currencyDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CurrencyDTO> updateCurrency(@PathVariable Long id, @RequestBody CurrencyDTO currencyDTO){
        return new ResponseEntity<CurrencyDTO>(currencyService.updateCurrency(id, currencyDTO), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id){
        currencyService.deleteCurrencyById(id);
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
}
