package com.thanksbucket.slack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlackServiceTest {

    @Autowired
    SlackService slackService;

    @Test
    void sendMessage() {
//        slackService.sendMessage("알림 테스트");
    }
}
