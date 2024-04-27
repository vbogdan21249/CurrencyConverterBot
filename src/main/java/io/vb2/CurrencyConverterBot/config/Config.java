package io.vb2.CurrencyConverterBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class Config {

    @Value("${key.currencyConverterApi}")
    private String currencyConverterApiApiKey;
}
