package io.vb2.CurrencyConverterBot.source;

import com.google.gson.Gson;

import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.utils.NetworkUtils;
import models.NBUCurrencyModel;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;


public class NBUSource extends ConverterSource {
    private static final Logger log = Logger.getLogger(NBUSource.class);
    public static final String SERVICE_NAME = "bank.gov.ua";

    @Override
    public BigDecimal rate(Currency from, Currency to) throws IOException, CurrencyConverterException {
        String collected = NetworkUtils.getBufferReaderByUrl(getUrlString(from), true);
        Gson gson = new Gson();
        NBUCurrencyModel[] currencyModels = gson.fromJson(String.valueOf(collected), NBUCurrencyModel[].class);
        try {
            NBUCurrencyModel model = currencyModels[0];
            return model.getCurRate();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            log.warn(Messages.getInvalidCurrencyMessage(e.getMessage()));
            throw new CurrencyConverterException(Messages.getInvalidCurrencyMessage("Currency not found. Try another source."));
        }

    }

    private String getUrlString(Currency from) {
        return "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?valcode=" + from.toString() + "&json";
    }
}
