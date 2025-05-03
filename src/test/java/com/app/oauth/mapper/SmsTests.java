package com.app.oauth.mapper;

import com.app.oauth.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SmsTests {

    @Autowired
    private SmsService smsService;

    @Test
    public void smsTest() {
        smsService.transferMessage("01085649679");
    }

    @Test
    public void emailTest() {
        smsService.sendEmailVerification("j22h.h0h@gmail.com");
    }

    @Test
    public void verifyAuthCodeTest() {
//        return session.getAttribute("verificationCode").equals(authCode);
    }
}

