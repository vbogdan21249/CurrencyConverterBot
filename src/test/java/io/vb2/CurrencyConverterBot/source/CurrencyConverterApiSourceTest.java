package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CurrencyConverterApiSourceTest {
    private CurrencyConverterApiSource currencyConverterApiSource;

    @BeforeEach
    public void setup() {
        currencyConverterApiSource = mock(CurrencyConverterApiSource.class);
    }

    @Test
    public void testRate_USD_EUR() throws IOException, CurrencyConverterException {
        when(currencyConverterApiSource.rate(Currency.USD, Currency.EUR)).thenReturn(BigDecimal.valueOf(1.2));

        BigDecimal result = currencyConverterApiSource.rate(Currency.USD, Currency.EUR);
        assertEquals(BigDecimal.valueOf(1.2), result);
    }

    @Test
    public void testRate_EUR_USD() throws IOException, CurrencyConverterException {
        when(currencyConverterApiSource.rate(Currency.EUR, Currency.USD)).thenReturn(BigDecimal.valueOf(0.83));

        BigDecimal result = currencyConverterApiSource.rate(Currency.EUR, Currency.USD);
        assertEquals(BigDecimal.valueOf(0.83), result);
    }
}
