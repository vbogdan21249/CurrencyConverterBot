package io.vb2.CurrencyConverterBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data

public class BotConfig {
    @Value("${bot.name}")
    String BOT_NAME;
    @Value("${bot.token}")
    String BOT_TOKEN;
}
