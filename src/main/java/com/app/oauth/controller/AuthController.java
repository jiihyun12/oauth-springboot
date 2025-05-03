package com.app.oauth.controller;

import com.app.oauth.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/*")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final SmsService smsService;

    @PostMapping("sendSms")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody String memberPhone){
        return smsService.sendEmailVerification(memberPhone);
    }

    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody String memberEmail){
        log.info(memberEmail);
        return smsService.sendEmailVerification(memberEmail);
    }

    @PostMapping("verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody String code){
        Map<String,Object> response = new HashMap<>();
        log.info("verifyCode {}", smsService.verifyAuthCode(code));

        boolean isFlag = smsService.verifyAuthCode(code);
        if(isFlag){
            response.put("message", "인증이 완료되었습니다");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "인증 번호를 확인해주세요😅");
            response.put("isflag", isFlag);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
