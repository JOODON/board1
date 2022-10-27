package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.Mbti;
import com.study.board.repository.MbtiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MbtiService {
    @Autowired
    private MbtiRepository mbtiRepository;

    public void mbtiWrite(Mbti mbti) throws Exception{
        mbtiRepository.save(mbti);
    }
    public Page<Mbti> boardList(Pageable pageable){
        return mbtiRepository.findAll(pageable);
        //보드라는 리스트에 녀석을 반환해줌!
    }
    public Mbti mbtiView(Integer id) throws Exception{
        return mbtiRepository.findById(id).get();
    }
}
