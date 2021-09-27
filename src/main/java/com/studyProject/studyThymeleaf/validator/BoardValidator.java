package com.studyProject.studyThymeleaf.validator;

import com.studyProject.studyThymeleaf.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return Board.class.equals(clazz);
    }
    public void validate(Object obj, Errors e) {
        Board board = (Board) obj;
        if(StringUtils.isEmpty(board.getContent())){
            e.rejectValue("content","key","내용을 입력해주세요");
        }
//        if(StringUtils.isEmpty(board.getTitle())){
//            e.rejectValue("title", "key","3글자 이상 30글자 미만으로 입력해주세요");
//        }
    }
}
