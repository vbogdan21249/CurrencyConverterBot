package io.vb2.CurrencyConverterBot;

import io.vb2.CurrencyConverterBot.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class CurrencyConverterBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterBotApplication.class, args);
//        ApiContextInitializer.init();
       // ApplicationContext ctx = new AnnotationConfigApplicationContext(BotConfig.class);

    }

}
