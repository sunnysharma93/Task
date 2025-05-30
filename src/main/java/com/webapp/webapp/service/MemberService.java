package com.webapp.webapp.service;

import com.webapp.webapp.dto.MemberDTO;
import com.webapp.webapp.entity.Member;
import com.webapp.webapp.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Member register(MemberDTO dto) {
        Optional<Member> existing = memberMapper.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        Member member = new Member();
        member.setEmail(dto.getEmail());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setUsername(dto.getUsername());
        member.setCreatedAt(LocalDateTime.now());
        memberMapper.insert(member);
        return member;
    }
    public Optional<Member> findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }

    public boolean verifyPassword(String raw, String encoded) {
        return passwordEncoder.matches(raw, encoded);
    }
}
