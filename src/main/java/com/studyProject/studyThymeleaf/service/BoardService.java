package com.studyProject.studyThymeleaf.service;

import com.studyProject.studyThymeleaf.model.Board;
import com.studyProject.studyThymeleaf.model.User;
import com.studyProject.studyThymeleaf.repository.BoardRepository;
import com.studyProject.studyThymeleaf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
