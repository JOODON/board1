package com.study.board.service;

import com.study.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

}
