package com.frankarrot.member.controller;

import com.frankarrot.member.domain.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MemberController {

    @GetMapping("/members")
    public ResponseEntity<Member> findMember() {
        final Member member = Member.builder()
                .id(1L)
                .name("test")
                .imageUrl("test.test")
                .build();
        return ResponseEntity.ok().body(member);
    }
}
