package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//컨트롤러라고 선언해주는 부분!
public class BoardController {
    @Autowired
    private BoardService boardService;

  @GetMapping("/board/write")
  public String boardWriteFrom() {

    return "BORADWrite";
  }
  @PostMapping("/board/writedo")
  public String boardWritePro(Board board){
        boardService.write(board);
        return "";
    }
    @GetMapping("board/list")
    public String boardList(Model model){
      model.addAttribute("list",boardService.boardList());
      //보드서비스안에 있는 보드 리스트라는 녀석을 리스트라는 이름을 가진애한테 옮기겠다!
      return "boardlist";
    }


}
