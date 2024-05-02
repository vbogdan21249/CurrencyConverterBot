package io.vb2.CurrencyConverterBot.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data

public class ApiConfig {
    @Value("${key.currencyConverterApi}")
    String currencyConverterApiApiKey;
}
