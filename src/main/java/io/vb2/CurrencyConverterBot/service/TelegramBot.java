package io.vb2.CurrencyConverterBot.service;

import io.vb2.CurrencyConverterBot.config.ApiConfig;
import io.vb2.CurrencyConverterBot.config.BotConfig;

import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.source.CurrencyConverterApiSource;
import io.vb2.CurrencyConverterBot.source.NBUSource;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger(TelegramBot.class);

    private final ApiConfig apiConfig;
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
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            // SETTING DEFAULT CURRENCY SOURCE
            if (Converter.getConverterSource() == null) {
                Converter.setConverterSource(new NBUSource());
            }

            // START COMMAND

            switch (messageText) {
                case "/start":
                    sendMessage(chatId, "Welcome to Currency Converter Bot! /help for more information.");
                    return;
                case "/help":
                    sendMessage(chatId, "Commands:\n" +
                            "/start - start the bot\n" +
                            "/changeSource - change currency source\n" +
                            "Enter two currencies to change them (e.g. USD UAH)\n" +
                            "Enter a number to convert it to the target currency\n");
                    return;
                case "/changeSource":
                    if (Converter.getConverterSource() instanceof CurrencyConverterApiSource) {
                        Converter.setConverterSource(new NBUSource());
                        currencyManager.updateConverter("UAH");
                        sendMessage(chatId, "NBU source has been set.\n" +
                                "It always has UAH as a target currency.\n" +
                                "Now you can convert " + currencyManager.getBaseCurrency() + " to " + currencyManager.getTargetCurrency() + "!");
                    } else {
                        Converter.setConverterSource(new CurrencyConverterApiSource(apiConfig));
                        sendMessage(chatId, "CurrencyConverterAPI source has been set.\n" +
                                "Now you can convert " + currencyManager.getBaseCurrency() + " to " + currencyManager.getTargetCurrency() + "!");

                    }
                    return;
                default:
                    // CONVERTING CURRENCY
                    if (messageText.matches("-?\\d+(\\.\\d+)?")) {
                        BigDecimal amount = new BigDecimal(messageText);
                        if (amount.compareTo(BigDecimal.ZERO) < 0) {
                            sendMessage(chatId, "Input cannot be negative.");
                        } else {
                            try {
                                sendMessage(chatId, messageText + " " +
                                        currencyManager.getBaseCurrency() + "\n" +
                                        currencyManager.convert(amount).toString()
                                        + " " + currencyManager.getTargetCurrency());
                            } catch (CurrencyConverterException e) {
                                sendMessage(chatId, e.getMessage());
                                log.error(e.getMessage());
                            } catch (IOException e) {
                                sendMessage(chatId, "Error occurred while converting currency.");
                                log.error(e.getMessage());
                                throw new RuntimeException(e);
                            }

                        }
                    }
                    break;
            }

            // CHANGING CURRENCIES
            String[] currencies = messageText.trim().split("\\s+");
            if (currencies.length == 2 && currencies[0].matches("\\b[a-zA-Z]{3,4}\\b") && currencies[1].matches("\\b[a-zA-Z]{3,4}\\b")) {

                String baseCurrency = currencies[0].toUpperCase();
                String targetCurrency = currencies[1].toUpperCase();

                if (Converter.getConverterSource() instanceof NBUSource) {
                    sendMessage(chatId, "NBU source always has UAH as a target currency.");
                    targetCurrency = "UAH";
                }
                try {
                    currencyManager.updateConverter(baseCurrency, targetCurrency);
                    sendMessage(chatId, "Currencies has been successfully changed.\n" +
                            "Now you can convert " + baseCurrency + " to " + targetCurrency + "!");
                } catch (CurrencyConverterException e) {
                    sendMessage(chatId, "Currencies has not been changed. " + e.getMessage());
                }
            }
        }
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            sendMessage(chatId, e.getMessage());
            log.error(e.getMessage());
        }
    }
}

