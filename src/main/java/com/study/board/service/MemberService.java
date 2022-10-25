package com.study.board.service;

import com.study.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberRepository login(String id) throws Exception{
        return memberRepository.findById(id).get();
    }
}
