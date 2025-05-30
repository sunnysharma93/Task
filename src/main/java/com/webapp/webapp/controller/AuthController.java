package com.webapp.webapp.controller;

import com.webapp.webapp.dto.MemberDTO;
import com.webapp.webapp.entity.Member;
import com.webapp.webapp.security.JwtUtil;
import com.webapp.webapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDTO dto) {
        Member member = memberService.register(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("email", member.getEmail());
        result.put("username", member.getUsername());
        result.put("createdAt", member.getCreatedAt());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberDTO dto) {
        Member member = memberService.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!memberService.verifyPassword(dto.getPassword(), member.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(member.getId());
        return ResponseEntity.ok(Map.of("token", token));
    }
    }
