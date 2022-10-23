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

    //글 작성
    public void write(Board board) {
        boardRepository.save(board);
        //이녀석이 자동으로 알아서해줌 레전드다..
    }
    //게시글 페이지
    public List<Board> boardList(){
        return boardRepository.findAll();
        //보드라는 리스트에 녀석을 반환해줌!
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }

}
