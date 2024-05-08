package io.vb2.CurrencyConverterBot.config;


import io.vb2.CurrencyConverterBot.service.TelegramBot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.context.TestPropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(properties = {
        "bot.name=MyBot",
        "bot.token=my_bot_token"
})

public class BotInitializerTest {

    @MockBean
    private TelegramBot telegramBot;

    @Autowired
    private BotInitializer botInitializer;

    @Test
    public void testInit() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = mock(TelegramBotsApi.class);
        telegramBotsApi.registerBot(telegramBot);

        botInitializer.init();
        verify(telegramBotsApi, times(1)).registerBot(telegramBot);
    }
}
