package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class CurrencyManagerTest {

//        @Test
//    public void testConvert() throws IOException, CurrencyConverterException {
//        Converter.setConverterSource(mock(ConverterSource.class));
//
//        CurrencyManager currencyManager = new CurrencyManager();
//        currencyManager.setBaseCurrency(Currency.USD);
//        currencyManager.setTargetCurrency(Currency.EUR);
//
//        BigDecimal result = currencyManager.convert(new BigDecimal("100"));
//
//         assertEquals(new BigDecimal("3939.6900"), result, "Conversion result should match expected value");
//    }

    @Test
    public void testConvertWithNullValue() {
        Converter.setConverterSource(mock(ConverterSource.class));

        CurrencyManager currencyManager = new CurrencyManager();
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
        CurrencyManager currencyManager = new CurrencyManager();

        currencyManager.updateConverter("USD", "EUR");

        assertEquals(Currency.USD, currencyManager.getBaseCurrency(), "Base currency should be updated");
        assertEquals(Currency.EUR, currencyManager.getTargetCurrency(), "Target currency should be updated");
    }

    @Test
    public void testUpdateConverterWithoutBase() throws CurrencyConverterException {
        CurrencyManager currencyManager = new CurrencyManager();

        currencyManager.updateConverter("EUR");

        assertEquals(Currency.USD, currencyManager.getBaseCurrency(), "Base currency should remain unchanged");
        assertEquals(Currency.EUR, currencyManager.getTargetCurrency(), "Target currency should be updated");
    }

    @Test
    public void testUpdateConverterWithInvalidCurrency() {
        CurrencyManager currencyManager = new CurrencyManager();

        // Test for an invalid currency
        assertThrows(CurrencyConverterException.class, () -> currencyManager.updateConverter("InvalidCurrency"));
    }
}
