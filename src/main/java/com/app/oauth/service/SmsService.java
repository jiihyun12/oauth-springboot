package com.app.oauth.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SmsService {

    //    문자 전송
    ResponseEntity<Map<String, Object>> transferMessage(String memberPhone);

    //    이메일 전송
    ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail);

    //    이메일 전송
    public ResponseEntity<Map<String, Object>> transferMessate(String memberPhone);

//    문자 전송
    public ResponseEntity<Map<String, Object>> sendVerification(String memberEmail);

//    인증코드 확인
    public boolean verifyAuthCode(String memberEmail);

}
