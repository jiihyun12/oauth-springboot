package com.app.oauth.mapper;

import com.app.oauth.domain.OauthMemberVO;
import com.app.oauth.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OauthMapperTests {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OauthMemberVO oauthMemberVO;

    @Test
    public void test() {

        log.info("{}", memberService.getMemberIdByMemberEmail("j22h.h0h@gmail.com"));
        log.info("{}", memberService.getMemberById(1L).orElse(null));
    }

    //    회원 조회 테스트
    @Test
    public void selectTest() {
        log.info( "{}", memberMapper.select(1L));
    }

//    회원 전체 조회 테스트
    @Test
    public void selectAllTest() {
        log.info(  "{}",memberMapper.selectAll());
    }

//    이메일로 아이디 찾기 테스트
    @Test
    public void selectByIdTest() {
        log.info( "{}" , memberMapper.select(1L));
    }

//    회원 가입 테스트
    @Test
    public void insertTest() {
        OauthMemberVO oauthMemberVO = new OauthMemberVO();
        oauthMemberVO.setMemberEmail("test1234@gmail.com");
        oauthMemberVO.setMemberPassword("1234");
        oauthMemberVO.setMemberName("홍길동");
        memberMapper.insert(oauthMemberVO);
    }

    @Test
    public void updateTest() {
         memberMapper.select(1L);
//        memberMapper.select(memberId).ifPresent(member ->{
//            OauthMemberVO oauthMemberVO = new OauthMemberVO();
//            oauthMemberVO.setId(member.getId());
//            oauthMemberVO.setMemberEmail(member.getMemberEmail());
//            oauthMemberVO.setMemberPassword("12345678");
//            oauthMemberVO.setMemberNickName("개복치 2단계");
//            oauthMemberVO.setMemberName(member.getMemberName());
//            oauthMemberVO.setMemberProvider(member.getMemberProvider());
//            memberMapper.update(oauthMemberVO);
//        } );
    }

    @Test
    void deleteTest() {
        memberMapper.select(1L);
    }
}

