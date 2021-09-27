package com.studyProject.studyThymeleaf.repository;

import com.studyProject.studyThymeleaf.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
