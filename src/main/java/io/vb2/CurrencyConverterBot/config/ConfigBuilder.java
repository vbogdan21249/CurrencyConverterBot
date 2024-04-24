package io.vb2.CurrencyConverterBot.config;

public class ConfigBuilder {

    private final Config config;

    public ConfigBuilder() {
        config = new Config();
    }

    public ConfigBuilder currencyConverterApiApiKey(String apiKey) {
        config.setCurrencyConverterApiApiKey(apiKey);

        return this;
    }
}
