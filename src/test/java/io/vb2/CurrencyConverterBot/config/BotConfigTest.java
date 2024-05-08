package io.vb2.CurrencyConverterBot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "bot.name=MyBot",
        "bot.token=myBotToken"
})

public class BotConfigTest {

    @Autowired
    private BotConfig botConfig;

    @Test
    public void testBotName() {
        assertEquals("MyBot", botConfig.getBOT_NAME(), "Значение BOT_NAME должно соответствовать ожидаемому");
    }

    @Test
    public void testBotToken() {
        assertEquals("myBotToken", botConfig.getBOT_TOKEN(), "Значение BOT_TOKEN должно соответствовать ожидаемому");
    }
}
