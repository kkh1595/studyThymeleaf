package com.studyProject.studyThymeleaf.controller;

import com.studyProject.studyThymeleaf.model.Board;
import com.studyProject.studyThymeleaf.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);
        return "board/list";
    }
    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("board", new Board());
        return "board/form";
    }

// 처음 생각해본 방법..create.html 을 만듬. 해당 html은 단순히 list.html로 가는 역할만 함. 비추천.. 리다이렉트 쓰자
//    @PostMapping("/create")
//    public String create(@ModelAttribute Board board){
//        boardRepository.save(board);
//        return "board/create";
//    }

    @PostMapping("/form")
    public String create(@ModelAttribute Board board){
        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/post")
    public String post(Model model, @RequestParam(required = false) Long id){
        System.out.println(id);
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board",board);
        return "board/post";
    }


}
