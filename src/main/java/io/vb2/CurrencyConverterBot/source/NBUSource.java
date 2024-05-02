package io.vb2.CurrencyConverterBot.source;

import com.google.gson.Gson;

import io.vb2.CurrencyConverterBot.enums.Currency;
import io.vb2.CurrencyConverterBot.utils.NetworkUtils;
import models.NBUCurrencyModel;

import java.io.IOException;
import java.math.BigDecimal;


public class NBUSource extends ConverterSource {
    public static final String SERVICE_NAME = "bank.gov.ua";

    @Override
    public BigDecimal rate(Currency from, Currency to) throws IOException {
        String collected = NetworkUtils.getBufferReaderByUrl(getUrlString(to), true);
        Gson gson = new Gson();
        NBUCurrencyModel[] currencyModels = gson.fromJson(String.valueOf(collected), NBUCurrencyModel[].class);
        NBUCurrencyModel model = currencyModels[0];
        return model.getCurRate();
    }

    private String getUrlString(Currency to) {
        return "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?valcode=" + to + "&json";
    }
}
