package io.vb2.CurrencyConverterBot.utils;

import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class NetworkUtilsTest {
    @Test
    public void testGet() throws IOException {
        String url = "https://api.exchangerate-api.com/v4/latest/USD";
        String response = NetworkUtils.getBufferReaderByUrl(url, true);

        assertNotNull(response, "Response should not be null");
    }

    @Test
    public void testGetWithInvalidUrl() {
        String url = "https://api.exchangerate-api.com/v4/latest/InvalidCurrency";
        assertThrows(CurrencyConverterException.class, () -> NetworkUtils.getBufferReaderByUrl(url, true));
    }
}