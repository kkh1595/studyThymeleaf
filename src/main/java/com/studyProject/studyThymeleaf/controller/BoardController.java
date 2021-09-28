package com.studyProject.studyThymeleaf.controller;

import com.studyProject.studyThymeleaf.model.Board;
import com.studyProject.studyThymeleaf.repository.BoardRepository;
import com.studyProject.studyThymeleaf.service.BoardService;
import com.studyProject.studyThymeleaf.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model model,
                       @PageableDefault(size = 3, sort="id", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber()-1);
        int endPage= Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber()+3);
        int totalPages = boards.getTotalPages();

        model.addAttribute("boards",boards);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("totalPages",totalPages);
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
    public String create(@Valid Board board, BindingResult bindingResult,Authentication authentication){
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }
        String username = authentication.getName();
        boardService.save(username, board);
//        boardRepository.save(board);
        return "redirect:/board/list";
    }

    @GetMapping("/post")
    public String post(Model model, @RequestParam(required = false) Long id, Principal principal){
        Board board = boardRepository.findById(id).orElse(null);
        String username = principal.getName();
        model.addAttribute("board",board);
        model.addAttribute("username", username);
        return "board/post";
    }
    @PostMapping("/post")
    public String modifyForm(Model model, @Valid Board board, @RequestParam(required = false) Long id,
                             BindingResult bindingResult){
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/modify";
        }
        boardRepository.save(board);
        model.addAttribute("board",board);
        return "board/post";
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam(required = false) Long id){
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board", board);
        return "board/modify";
    }
}
