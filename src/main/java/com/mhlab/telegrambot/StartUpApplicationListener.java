package com.mhlab.telegrambot;

import com.mhlab.telegrambot.telegram.TelegramMessageBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

/**
 * Created by MHLab on 26/08/2019..
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class StartUpApplicationListener {

    private final TelegramMessageBot telegramMessageBot;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), TimeZone.getDefault().toZoneId());
        String startMsg = "\n===== My Application =====\n"
                + "=== SERVER START === \n\n"
                + "[Active Profile] : MY-PROFILE"
                + "\n[Up-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        log.error(startMsg);

        telegramMessageBot.sendMessage(startMsg);
    }

    @EventListener
    public void shutdownApplicationEvent(ContextClosedEvent event) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(event.getTimestamp()), TimeZone.getDefault().toZoneId());
        String endMsg = "\n===== My Application =====\n"
                + "=== SERVER DOWN === \n\n"
                + "[Active Profile] : MY-PROFILE"
                + "\n[Down-Time] : " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        log.error(endMsg);

        telegramMessageBot.sendMessage(endMsg);
    }

}
