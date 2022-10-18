package com.study.board.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
//이거해주면 게터세터의 효과를 해줌
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //알아서 처리해줌
    private Integer id;
    private String title;
    private String content;

}
