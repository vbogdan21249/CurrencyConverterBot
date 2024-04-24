package io.vb2.CurrencyConverterBot.source;

import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.utils.NetworkUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

public class NBUSource {
    public static final String SERVICE_NAME = "";
    public BigDecimal rate(String apiKey, Currency from, Currency to) throws IOException {
        String collected = NetworkUtils.getBufferReaderByUrl(getUrlString(to), true);

        String[] splitCurrencyInfo = collected.split(":");
        if (splitCurrencyInfo.length != 2) {
            throw new CurrencyConverterException(Messages.getServiceUnavailableMessage(SERVICE_NAME));
        }

        String rateString = splitCurrencyInfo[1].replace("}", "");
        return new BigDecimal(rateString);
    }

    private String getUrlString(Currency to) {
        return "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?valcode=" + to + "&json";
    }
}
