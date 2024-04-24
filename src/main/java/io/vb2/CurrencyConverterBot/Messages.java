package io.vb2.CurrencyConverterBot;

public class Messages {
    public static final String TRY_LATER = "No working services at the moment. Try again later. If the problem stays, create a ticket on github.com/posadskiy/currency-converter.";

    public static String getServiceUnavailableMessage(String serviceName) {
        return serviceName + " public API was changed. Please, create ticket on github.com/posadskiy/currency-converter.";
    }

    public static String getServiceNetworkErrorMessage(String serviceResponse) {
        return "Service unavailable. Response: " + serviceResponse;
    }

    public static String getInvalidCurrencyMessage(String exceptionMessage) {
        return "Error updating currencies: " + exceptionMessage;
    }
}
