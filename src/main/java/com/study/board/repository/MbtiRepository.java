package com.study.board.repository;

import com.study.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MbtiRepository extends JpaRepository<Board,Integer> {

}
