package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
        //이녀석이 자동으로 알아서해줌 레전드다..
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
        //보드라는 리스트에 녀석을 반환해줌!
    }
}
