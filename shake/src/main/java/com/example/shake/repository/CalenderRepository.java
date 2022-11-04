package com.example.shake.repository;

import com.example.shake.entity.Calendar;
import com.example.shake.entity.CongressOfMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JPA를 사용할 때는 엔티티의 타입정보(Memo)와 @Id 타입(Long)을 지정한다
// JPA는 인터페이스 선언만으로도 자동으로 스프링의 빈(Bean)으로 등록된다
@Repository
public interface CalenderRepository extends JpaRepository<Calendar,Long> {

}

