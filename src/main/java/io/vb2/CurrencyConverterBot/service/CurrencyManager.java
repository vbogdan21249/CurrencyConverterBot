package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class CurrencyManager {
    private Currency baseCurrency = Currency.UAH;
    private Currency targetCurrency = Currency.USD;

    public BigDecimal convert(BigDecimal value) throws IOException {
        return value.multiply(Converter.convert(baseCurrency, targetCurrency));
    }

    public boolean updateConverter(String baseToUpdate, String targetToUpdate) {
        try {
            baseCurrency = Currency.valueOf(baseToUpdate.toUpperCase());
            targetCurrency = Currency.valueOf(targetToUpdate.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {

            throw new CurrencyConverterException(Messages.getInvalidCurrencyMessage(e.getMessage()));
        }
    }

    public void getCurrencies() {
        System.out.println(baseCurrency.toString());
        System.out.println(targetCurrency.toString());
    }
}
