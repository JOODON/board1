package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.Mbtidb;
import com.study.board.repository.MbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MbtiService {
    @Autowired
    private MbtiRepository mbtiRepository;

    public void mbtiWrite(Mbtidb mbti) throws Exception{
        mbtiRepository.save(mbti);
    }
    public List<Mbtidb> mbtiList(){
        return mbtiRepository.findAll();
        //보드라는 리스트에 녀석을 반환해줌!
    }
}
