package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConverterTest {

    @Test
    public void testConvertSameCurrency() throws IOException, CurrencyConverterException {
        ConverterSource mockConverterSource = mock(ConverterSource.class);
        Converter.setConverterSource(mockConverterSource);

        Currency currency = Currency.USD;

        BigDecimal result = Converter.convert(currency, currency);

        assertEquals(BigDecimal.ONE, result, "Conversion should return 1 for same currency");
    }


    @Test
    public void testConvertDifferentCurrency() throws IOException, CurrencyConverterException {
        ConverterSource mockConverterSource = mock(ConverterSource.class);
        Converter.setConverterSource(mockConverterSource);

        Currency from = Currency.USD;
        Currency to = Currency.EUR;
        BigDecimal conversionRate = BigDecimal.valueOf(1.2); // Dummy conversion rate

        when(mockConverterSource.rate(from, to)).thenReturn(conversionRate);

        BigDecimal result = Converter.convert(from, to);

        assertEquals(conversionRate, result, "Conversion should return correct conversion rate");
    }
}