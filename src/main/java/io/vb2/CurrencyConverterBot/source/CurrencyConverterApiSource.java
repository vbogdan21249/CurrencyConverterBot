package io.vb2.CurrencyConverterBot.source;

import java.io.IOException;
import java.math.BigDecimal;

import io.vb2.CurrencyConverterBot.config.BotConfig;
import io.vb2.CurrencyConverterBot.config.Config;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.utils.NetworkUtils;

public class CurrencyConverterApiSource extends ConverterSource {
    public static final String SERVICE_NAME = "CurrencyConverterApi.Com";

    @Override
    public BigDecimal rate(Currency from, Currency to) throws IOException {
        String collected = NetworkUtils.getBufferReaderByUrl(getUrlString(from, to), true);

        String[] splitCurrencyInfo = collected.split(":");
        if (splitCurrencyInfo.length != 2) {
            throw new CurrencyConverterException(Messages.getServiceUnavailableMessage(SERVICE_NAME));
          }

        String rateString = splitCurrencyInfo[1].replace("}", "");
        return new BigDecimal(rateString);
    }

    private String getUrlString(Currency from, Currency to) {
        return "https://free.currconv.com/api/v7/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=" + config.getCurrencyConverterApiApiKey();
    }
}
