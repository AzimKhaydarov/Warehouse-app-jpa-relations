package com.example.warehouseappjparelations.controller;

import com.example.warehouseappjparelations.entity.Currency;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.CurrencyRepository;
import com.example.warehouseappjparelations.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyRepository currencyRepository;

    @PostMapping
    public Result addCurrency(@RequestBody Currency currency) {
        Result result = currencyService.addCurrencyService(currency);
        return result;
    }

    @GetMapping("/all")
    public List<Currency> getCurrencys() {
        List<Currency> currencys = currencyService.getCurrencys();
        return currencys;
    }

    @GetMapping("/{id}")
    public Object getCurrency(@PathVariable Integer id) {
        Object result = currencyService.getCurrency(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editCurrency(@RequestBody Currency currency, @PathVariable Integer id) {
        Result result = currencyService.editCurrencyService(currency, id);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id) {
        Result result = currencyService.deleteCurrency(id);
        return result;
    }
}
