package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.entity.Mbtidb;
import com.study.board.entity.Member;
import com.study.board.service.BoardService;
import com.study.board.service.MbtiService;
import com.study.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
//컨트롤러라고 선언해주는 부분!
public class BoardController {
  @Autowired
  private BoardService boardService;
  @Autowired
  private MemberService memberService;

  @Autowired
  private MbtiService mbtiService;

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
  public String boardList(Model model,
                          @PageableDefault(page = 0, size = 1 , sort ="id" ,direction =Sort.Direction.DESC) Pageable pageable,
                          String searchKeyWord) {

    Page<Board> list=null;

    if(searchKeyWord ==null){
      list=boardService.boardList(pageable);
    }
    else {
      list=boardService.boardSearchList(searchKeyWord,pageable);
    }

    int nowPage=list.getPageable().getPageNumber()+1;
    //현재 페이지를 받아옴
    int stratPage=Math.max(nowPage -4 ,1);
    //시작 페이지 가장 뒤가 9이면 그 앞이 1
    int endPage=Math.min(nowPage +5,list.getTotalPages());
    //마지막 페이지가 5 가장 마지막일경우에는 TotalPage로 설정
    model.addAttribute("list",list);
    model.addAttribute("nowPage",nowPage);
    model.addAttribute("startPage",stratPage);
    model.addAttribute("endPage",endPage);


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

  @GetMapping("/board/login")
  public String boardloginfrom() {

    return "loginPage";
  }
  @PostMapping("/board/loginPro")
  public String boardlogin(Member member,Model model) {
    int result=memberService.login(member.getUserid(), member.getUserpassword());

    if (result==1) {
      model.addAttribute("message", "로그인에 성공하셨습니다");
      model.addAttribute("searchUrl", "/board/list");
    }
    else if (result==0) {
      model.addAttribute("message", "비밀번호가 틀립니다");
      model.addAttribute("searchUrl", "/board/login");
    }
    else if (result==-1) {
      model.addAttribute("message", "존재하지않는 아이디입니다");
      model.addAttribute("searchUrl", "/board/login");
    }
    else if (result==-2) {
      model.addAttribute("message", "데이터 베이스의 오류가 발견되었습니다");
      model.addAttribute("searchUrl", "/board/login");
    }
    return "message";
  }
  @GetMapping("/mbti/list")
  public String mbtilist(Model model,@PageableDefault(page=1,size=1,sort ="id",direction=Sort.Direction.ASC) Pageable pageable) {

    model.addAttribute("list",mbtiService.mbtiList(pageable));

    //현재 페이지 넘버를 넘겨줌
    return "mbtiPage";
  }
}