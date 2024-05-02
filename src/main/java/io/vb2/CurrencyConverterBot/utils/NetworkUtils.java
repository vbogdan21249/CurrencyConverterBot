package io.vb2.CurrencyConverterBot.utils;

import io.vb2.CurrencyConverterBot.exception.CurrencyConverterException;
import io.vb2.CurrencyConverterBot.Messages;
import io.vb2.CurrencyConverterBot.source.ConverterSource;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NetworkUtils {
    private static final Logger log = Logger.getLogger(ConverterSource.class);
    public static String getBufferReaderByUrl(String urlString, boolean isHttps) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = isHttps ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setRequestProperty("Cache-Control", "no-cache");

        if (conn.getResponseCode() != 200) {
            Scanner s = new Scanner(conn.getErrorStream()).useDelimiter("\\A");
            String serviceNetworkErrorMessage = s.hasNext() ? s.next() : "";

            log.warn(Messages.getServiceNetworkErrorMessage(serviceNetworkErrorMessage));
            throw new CurrencyConverterException(Messages.getServiceNetworkErrorMessage(serviceNetworkErrorMessage));
        }

        String currency = new BufferedReader(
                new InputStreamReader(
                        conn.getInputStream()))
                .lines()
                .collect(Collectors.joining());

        conn.disconnect();

        return currency;
    }
}
