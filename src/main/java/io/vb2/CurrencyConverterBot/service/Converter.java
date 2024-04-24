package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.config.Config;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import io.vb2.CurrencyConverterBot.source.NBUSource;
import lombok.Setter;
import models.CurrencyRate;

import java.io.IOException;
import java.math.BigDecimal;

public class Converter {
    @Setter
    private static ConverterSource converterSource = new NBUSource();

    public static BigDecimal convert(Currency from, Currency to) throws IOException {
        return converterSource.rate(from, to);
    }
}
