package com.mhlab.telegrambot;

import com.mhlab.telegrambot.telegram.TelegramMessageBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) {
		ApiContextInitializer.init();

		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new TelegramMessageBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
		
		SpringApplication.run(TelegramBotApplication.class, args);
	}

}
