package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CurrencyManagerTest {

    private CurrencyManager currencyManager;

    @BeforeEach
    public void setUp() {
        currencyManager = new CurrencyManager();
    }

    @Test
    public void testConvertWithNullValue() {
        Converter.setConverterSource(mock(ConverterSource.class));

        currencyManager.setBaseCurrency(Currency.USD);
        currencyManager.setTargetCurrency(Currency.EUR);

        assertThrows(CurrencyConverterException.class, () -> {
            currencyManager.convert(null);
        });

        CurrencyConverterException exception = assertThrows(CurrencyConverterException.class, () -> {
            currencyManager.convert(null);
        });
        assertEquals("Value cannot be null.", exception.getMessage());
    }

    @Test
    public void testUpdateConverterWithBase() throws CurrencyConverterException {
        currencyManager.updateConverter("USD", "EUR");

        assertEquals(Currency.USD, currencyManager.getBaseCurrency());
        assertEquals(Currency.EUR, currencyManager.getTargetCurrency());
    }

    @Test
    public void testUpdateConverterWithoutBase() throws CurrencyConverterException {

        currencyManager.updateConverter("EUR");

        assertEquals(Currency.USD, currencyManager.getBaseCurrency());
        assertEquals(Currency.EUR, currencyManager.getTargetCurrency());
    }

    @Test
    public void testUpdateConverterWithInvalidCurrency() {
        assertThrows(CurrencyConverterException.class, () -> currencyManager.updateConverter("InvalidCurrency"));
    }
}
