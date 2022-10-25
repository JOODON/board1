package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //글 작성
    public void write(Board board, MultipartFile file) throws Exception{

        String proJectPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
        //프로젝트 경로를 담아줌
        UUID uuid=UUID.randomUUID();
        //랜덤으로 식별자의 이름을 정해줌

        String fileName =uuid + "_"+ file.getOriginalFilename();
        //파일의 이름을 지정해줌

        File saveFile=new File(proJectPath,fileName);
        //경로를 지정해주고 파일의 이름을 넣어줌

        board.setFilename(fileName);

        board.setFilepath("/files/"+fileName);

        file.transferTo(saveFile);

        boardRepository.save(board);

        //이녀석이 자동으로 알아서해줌 레전드다..
    }
    //게시글 페이지
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
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
