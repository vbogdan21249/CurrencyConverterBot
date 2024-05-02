package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.config.ApiConfig;
import io.vb2.CurrencyConverterBot.enums.Currency;



import org.springframework.stereotype.Component;
import java.io.IOException;
import java.math.BigDecimal;

@Component
public abstract class ConverterSource {
    ApiConfig apiConfig;

    public abstract BigDecimal rate(Currency from, Currency to) throws IOException;
}
