package com.thanksbucket.slack;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SlackService {

    @Value("${spring.profiles.active}")
    private String env;

    @Value("${slack.webhook.url}")
    private String SLACK_WEBHOOK_URL;
    private final Slack slack = Slack.getInstance();

    public void sendSignupMessage(String email, String nickname) {
        sendMessage(String.format("[%s] 회원가입 - email: %s / nickname: %s 님이 신규가입했어요\uD83C\uDF89\uD83C\uDF89", env, email, nickname));
    }

    private void sendMessage(String text) {
        try {
            WebhookResponse response = slack.send(
                    SLACK_WEBHOOK_URL,
                    Payload.builder()
                            .text(text)
                            .build());

            if (response.getCode() != 200) {
                log.error("Failed to send message to slack. code: {}", response.getCode());
                log.error("response: {}", response.getBody());
                return;
            }
            log.info("Successfully sent message to slack");
        } catch (Exception e) {
            log.error("Failed to send message to slack", e);
        }
    }
}


