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

public class NBUSourceTest {
    private NBUSource nbuSource;

    @BeforeEach
    public void setup() {
        nbuSource = mock(NBUSource.class);
    }

    @Test
    public void testRate_UAH_EUR() throws IOException, CurrencyConverterException {
        when(nbuSource.rate(Currency.UAH, Currency.EUR)).thenReturn(BigDecimal.valueOf(1.2));

        BigDecimal result = nbuSource.rate(Currency.UAH, Currency.EUR);
        assertEquals(BigDecimal.valueOf(1.2), result);
    }
}
