//package io.vb2.CurrencyConverterBot.service.CurrencyRateProviders;
//
//import com.google.gson.Gson;
//import io.vb2.CurrencyConverterBot.service.CurrencyRateService.CurrencyRateService;
//import models.NBUCurrencyModel;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.Scanner;
//
//public class NBUCurrencyRateProvider {
//    //    @Override
//    public String getCurrencyRate() throws IOException {
//        URL url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?valcode="  + "&json");
//        Scanner scanner = new Scanner((InputStream) url.getContent());
//        StringBuilder result = new StringBuilder();
//        while (scanner.hasNext()) {
//            result.append(scanner.nextLine());
//        }
//
//        Gson gson = new Gson();
//        NBUCurrencyModel[] currencyModels = gson.fromJson(String.valueOf(result), NBUCurrencyModel[].class);
//        NBUCurrencyModel model = currencyModels[0];
//
//        return "Official rate of UAH to " + model.getCurAbbreviation() + "\n" +
//                "on the date " + model.getExchangeDate() + "\n" +
//                "is " + model.getCurRate() + " UAH " + "per 1 " + model.getCurAbbreviation();
//
//    }
//}
//
