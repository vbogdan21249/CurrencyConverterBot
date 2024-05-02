package io.vb2.CurrencyConverterBot.source;

import java.io.IOException;
import java.math.BigDecimal;

import io.vb2.CurrencyConverterBot.config.ApiConfig;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;


@Slf4j
public class CurrencyConverterApiSource extends ConverterSource {
    //    private static final Logger log = Logger.getLogger(ConverterSource.class);
    public static final String SERVICE_NAME = "currencyconverterapi.com";

    public CurrencyConverterApiSource(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    @Override
    public BigDecimal rate(Currency from, Currency to) throws IOException {

        String collected = NetworkUtils.getBufferReaderByUrl(getUrlString(from, to), true);

        String[] splitCurrencyInfo = collected.split(":");
        if (splitCurrencyInfo.length != 2) {
            log.error(Messages.getServiceUnavailableMessage(SERVICE_NAME));
            throw new CurrencyConverterException(Messages.getServiceUnavailableMessage(SERVICE_NAME));
        }
        String rateString = splitCurrencyInfo[1].replace("}", "");
        return new BigDecimal(rateString);
    }

    private String getUrlString(Currency from, Currency to) {
        return "https://free.currconv.com/api/v7/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=" + apiConfig.getCurrencyConverterApiApiKey();
    }


}
