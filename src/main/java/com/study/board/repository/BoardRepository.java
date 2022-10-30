package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Integer> {
    //어떤 DTO를 참고할것인가를 먼저 선언하고 프라이머리키로 설저된 녀석을받아줌!
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
