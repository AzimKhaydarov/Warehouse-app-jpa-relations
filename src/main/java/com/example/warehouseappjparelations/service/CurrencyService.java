package com.example.warehouseappjparelations.service;

import com.example.warehouseappjparelations.entity.Currency;
import com.example.warehouseappjparelations.payload.Result;
import com.example.warehouseappjparelations.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrencyService(Currency currency) {
        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result("Currency unit already exists!", false);
        Currency saved = currencyRepository.save(currency);
        return new Result("Currency unit added successfully!", true);
    }

    public Result editCurrencyService(Currency currency, Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        Currency currency1 = optionalCurrency.get();
        if (!optionalCurrency.isPresent()) return new Result("The currency unit not found!", false);
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return new Result("Currency edited successfully!", true);

    }
    public List<Currency> getCurrencys() {
        List<Currency> currencys = currencyRepository.findAll();
        return currencys;
    }
    public Object getCurrency(@PathVariable Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if(!optionalCurrency.isPresent()) return new Result("The currency unit with current id not found", false);
        return optionalCurrency.get();}

    public Result deleteCurrency(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (!optionalCurrency.isPresent()) return new Result("The currency unit not found!", false);
        Currency currency1 = optionalCurrency.get();
        currencyRepository.delete(currency1);
        return new Result("The currency deleted successfully!", true);
    }
}
