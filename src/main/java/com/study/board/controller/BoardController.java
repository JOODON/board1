package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
//컨트롤러라고 선언해주는 부분!
public class BoardController {
  @Autowired
  private BoardService boardService;

  //생성자 주입 객체 (이 객체를 가져다가 쓴다라고 하면댐)
  @GetMapping("/board/write")
  public String boardWriteFrom() {

    return "BORADWrite";
  }

  @PostMapping("/board/writedo")
  public String boardWritePro(Board board, Model model,MultipartFile file) throws Exception {
    boardService.write(board,file);
    //if문을 써서 한번 더 확인가능!
    model.addAttribute("message","글 작성이 완료 되었습니다");

    model.addAttribute("searchUrl","/board/list");

    return "message";
    //이거는 바로 넘어갈떄가 아니고 어떤 기능을 구현하고 돌아갈떄 붙혀줘야됨
  }

  @GetMapping("/board/list")
  public String boardList(Model model) {
    model.addAttribute("list", boardService.boardList());
    //보드서비스안에 있는 보드 리스트라는 녀석을 리스트라는 이름을 가진애한테 옮기겠다!
    return "boardlist";
  }

  @GetMapping("/board/view")
  //localhost8080/board/view? id=()이부분이 아이디값으로 들어감
  public String boardview(Model model, Integer id) {
    model.addAttribute("board", boardService.boardView(id));
    return "BoardView";
  }

  @GetMapping("/board/delete")
  public String delete(Integer id) {
    boardService.boardDelete(id);
    return "redirect:/board/list";
  }

  @GetMapping("/board/modify/{id}")
  public String modify(@PathVariable("id") Integer id,Model model) {

    model.addAttribute("board", boardService.boardView(id));


    return "boardmodify";
  }

  @PostMapping("/board/update/{id}")
  public String boardupdate(@PathVariable("id") Integer id,Board board,Model model,MultipartFile file) throws Exception{

    Board boardTemp=boardService.boardView(id);

    boardTemp.setTitle(board.getTitle());

    boardTemp.setContent(board.getContent());

    boardService.write(boardTemp,file);

    model.addAttribute("message","글 수정이 완료 되었습니다");

    model.addAttribute("searchUrl","/board/list");

    return "message";
  }
}