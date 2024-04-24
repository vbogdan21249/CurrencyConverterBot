package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.config.Config;
import io.vb2.CurrencyConverterBot.enums.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public abstract class ConverterSource {
    protected Config config;
    public abstract BigDecimal rate(Currency from, Currency to) throws IOException;
}
