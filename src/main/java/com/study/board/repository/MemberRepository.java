package com.study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberRepository,String>{
    //JPA사용 하려고 키값으로 String값을 가져오는걸 확인
}
