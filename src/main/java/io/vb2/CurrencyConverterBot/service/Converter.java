package io.vb2.CurrencyConverterBot.service;


import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.ConverterSource;

import io.vb2.CurrencyConverterBot.source.NBUSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public class Converter {
    @Getter
    @Setter
    private static ConverterSource converterSource;
    static {
        converterSource = new NBUSource();
    }
    public static BigDecimal convert(Currency from, Currency to) throws IOException, CurrencyConverterException {
        if (from.equals(to)) {
            return BigDecimal.ONE;
        }
        return converterSource.rate(from, to);
    }
}
