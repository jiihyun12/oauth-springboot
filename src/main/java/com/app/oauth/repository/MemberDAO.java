package com.app.oauth.repository;

import com.app.oauth.domain.OauthMemberVO;
import com.app.oauth.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;

//    회원 조회
    public Optional<OauthMemberVO> findById(Long id) {
        return memberMapper.select(id);
    }

//    회원 전체 조회
    public List<OauthMemberVO> findAll() {
        return memberMapper.selectAll();
    }

//    이메일로 아이디 찾기
    public Long findByEmail(String email) {
        return memberMapper.selectByEmail(email);
    }

    public void save(OauthMemberVO oauthMemberVO) {
            memberMapper.insert(oauthMemberVO);
    }

    public void update(OauthMemberVO oauthMemberVO) {
            memberMapper.update(oauthMemberVO);
    }

    public void delete(Long id) {
            memberMapper.delete(id);
    }

}
