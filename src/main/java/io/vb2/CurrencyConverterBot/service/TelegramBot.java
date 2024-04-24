package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.config.BotConfig;
import io.vb2.CurrencyConverterBot.service.ConverterService.CurrencyConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private final BotConfig config;
    private final CurrencyManager currencyManager;
    @Override
    public String getBotUsername() {
        return config.getBOT_NAME();
    }

    @Override
    public String getBotToken() {
        return config.getBOT_TOKEN();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if (messageText.matches("\\b[a-zA-Z]{3,4}\\b\\s[a-zA-Z]{3,4}\\b")){
                currencyManager.updateConverter(baseCurrency, targetCurrency);
            }

//              SendMessage message = new SendMessage(); // Create a message object object
//            message.setChatId(chatId);
//            message.setText(messageText);
//            try {
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                log.error("Error occurred: " + e.getMessage());
//            }
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }
}

