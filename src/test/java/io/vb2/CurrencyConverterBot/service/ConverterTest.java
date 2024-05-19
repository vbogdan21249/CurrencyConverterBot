package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ConverterTest {

    @Mock
    private ConverterSource mockConverterSource;

    @InjectMocks
    private Converter converter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConvertSameCurrency() throws IOException, CurrencyConverterException {
        Currency usd = Currency.USD;
        BigDecimal result = Converter.convert(usd, usd);
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    public void testConvertDifferentCurrencies() throws IOException, CurrencyConverterException {
        Currency usd = Currency.USD;
        Currency eur = Currency.EUR;
        BigDecimal expectedRate = new BigDecimal("0.85");

        when(mockConverterSource.rate(usd, eur)).thenReturn(expectedRate);
        Converter.setConverterSource(mockConverterSource);

        BigDecimal result = Converter.convert(usd, eur);
        assertEquals(expectedRate, result);
    }

    @Test
    public void testConvertThrowsException() throws IOException, CurrencyConverterException {
        Currency usd = Currency.USD;
        Currency eur = Currency.EUR;

        when(mockConverterSource.rate(usd, eur)).thenThrow(new CurrencyConverterException("Currency converter error"));

        Converter.setConverterSource(mockConverterSource);
        assertThrows(CurrencyConverterException.class, () -> Converter.convert(usd, eur));
    }
}