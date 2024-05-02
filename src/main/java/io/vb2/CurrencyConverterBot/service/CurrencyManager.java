package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.math.BigDecimal;
import org.apache.log4j.Logger;


@Component
public class CurrencyManager {
    private static final Logger log = Logger.getLogger(CurrencyManager.class);
    private Currency baseCurrency = Currency.USD;
    private Currency targetCurrency = Currency.UAH;

    public BigDecimal convert(BigDecimal value) throws IOException {
        return value.multiply(Converter.convert(baseCurrency, targetCurrency));
    }

    public boolean updateConverter(String baseToUpdate, String targetToUpdate) {
        try {
            baseCurrency = Currency.valueOf(baseToUpdate.toUpperCase());
            targetCurrency = Currency.valueOf(targetToUpdate.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            log.error(Messages.getInvalidCurrencyMessage(e.getMessage()));
            throw new CurrencyConverterException(Messages.getInvalidCurrencyMessage(e.getMessage()));
        }
    }
}
