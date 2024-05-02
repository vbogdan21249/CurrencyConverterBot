package io.vb2.CurrencyConverterBot;

public class Messages {
    public static final String TRY_LATER = "No working services at the moment. Try again later.";

    public static String getServiceUnavailableMessage(String serviceName) {
        return serviceName + " service is unavailable. Try again later or change it with /changeSource.";
    }

    public static String getServiceNetworkErrorMessage() {
        return "Service unavailable for now. Try again later or change it with /changeSource";
    }

    public static String getInvalidCurrencyMessage(String exceptionMessage) {
        return "Error updating currencies: " + exceptionMessage;
    }

}
