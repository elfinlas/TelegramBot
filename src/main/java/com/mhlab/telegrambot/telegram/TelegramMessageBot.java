package com.mhlab.telegrambot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Created by mhlab(dex) on 2019-08-26.
 */

@Slf4j
@Component
public class TelegramMessageBot extends TelegramLongPollingBot { //
    private final String BOT_NAME = "YOUR_BOT_NAME"; //Bot Name
    private final String AUTH_KEY = "YOUR_BOT_AUTH_KEY"; //Bot Auth-Key
    private final String CHAT_ID = "YOUR_CHAT_ID"; //Chat ID

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return AUTH_KEY;
    }

    /**
     * 메세지를 받으면 처리하는 로직
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        log.info("update = " + update);
    }

    /**
     * 메세지 전달
     * @param sendMessage
     */
    public void sendMessage(String sendMessage) {
        SendMessage message = new SendMessage()
                .setChatId(CHAT_ID)
                .setText(sendMessage);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
