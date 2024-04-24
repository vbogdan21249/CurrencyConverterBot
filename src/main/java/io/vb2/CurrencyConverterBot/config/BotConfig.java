package io.vb2.CurrencyConverterBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Data

public class BotConfig {
    @Value("${bot.name}")
    String BOT_NAME;
    @Value("${bot.token}")
    String BOT_TOKEN;
}
