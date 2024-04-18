package io.vb2.CurrencyConverterBot.service;

import com.google.gson.Gson;
import models.CurrencyModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class CurrencyService {
    public static String getCurrencyRate(String message, CurrencyModel model) throws IOException {
        URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?valcode=" + message + "&json");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        StringBuilder result = new StringBuilder();
        while (scanner.hasNext()) {
            result.append(scanner.nextLine());
        }

        Gson gson = new Gson();
        CurrencyModel[] currencyModels = gson.fromJson(String.valueOf(result), CurrencyModel[].class);
        model = currencyModels[0];

        return "Official rate of UAH to " + model.getCurAbbreviation() + "\n" +
                "on the date " + model.getExchangeDate() + "\n" +
                "is " + model.getCurRate() + " UAH " + "per 1 " + model.getCurAbbreviation();
    }
}