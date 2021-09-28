package com.studyProject.studyThymeleaf.repository;

import com.studyProject.studyThymeleaf.model.Board;
import com.studyProject.studyThymeleaf.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @EntityGraph(attributePaths ={"boards"})
    List<User> findAll();
}
