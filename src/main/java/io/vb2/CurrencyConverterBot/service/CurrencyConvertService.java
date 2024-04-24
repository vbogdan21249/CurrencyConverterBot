package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.config.Config;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.CurrencyConverterApiSource;

import java.math.BigDecimal;

public class CurrencyConvertService {

    public BigDecimal rate(Config config, Currency from, Currency to) {
        if (from == to) return new BigDecimal("1.0");
        if (config.getCurrencyConverterApiApiKey() != null) {
            CurrencyConverterApiSource currencyConverterApiSource = new CurrencyConverterApiSource();
            try {
                return currencyConverterApiSource.rate(config.getCurrencyConverterApiApiKey(), from, to);
            } catch (CurrencyConverterException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new CurrencyConverterException(Messages.TRY_LATER);
    }
}
