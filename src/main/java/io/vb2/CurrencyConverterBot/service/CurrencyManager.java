package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.math.BigDecimal;

@Getter
@Setter
public class CurrencyManager {
    private Currency baseCurrency;
    private Currency targetCurrency;

    public BigDecimal convert(BigDecimal value) throws IOException {
        return value.multiply(Converter.convert(baseCurrency, targetCurrency));
    }

    public void updateConverter(String baseToUpdate, String targetToUpdate) {
        baseCurrency = Currency.valueOf(baseToUpdate);
        targetCurrency = Currency.valueOf(targetToUpdate);
    }
}
