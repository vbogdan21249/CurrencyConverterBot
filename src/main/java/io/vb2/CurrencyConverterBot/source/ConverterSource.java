package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.enums.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public interface ConverterSource {
    BigDecimal rate(String apiKey, Currency from, Currency to) throws IOException;
}
