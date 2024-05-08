package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConverterSourceTest {
    @Test
    public void testRate() throws IOException, CurrencyConverterException {
        ConverterSource converterSource = mock(ConverterSource.class);

        when(converterSource.rate(Currency.USD, Currency.EUR)).thenReturn(BigDecimal.valueOf(1.2));

        BigDecimal result = converterSource.rate(Currency.USD, Currency.EUR);
        assertEquals(BigDecimal.valueOf(1.2), result);
    }
}
