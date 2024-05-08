package io.vb2.CurrencyConverterBot.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@SpringBootTest
@TestPropertySource(properties = {
      "key.currencyConverterApi=myApiKey"
})
public class ApiConfigTest {
    @Autowired
    private ApiConfig apiConfig;
    @Test
    public void testCurrencyConverterApiApiKey() {
        assertEquals("myApiKey", apiConfig.getCurrencyConverterApiApiKey());
    }
}
