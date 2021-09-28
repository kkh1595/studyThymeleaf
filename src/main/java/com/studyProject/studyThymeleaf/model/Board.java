package com.studyProject.studyThymeleaf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min=3,max=30, message ="3글자 이상 30글자 미만으로 작성해야합니다")
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name ="user_id")
    @JsonIgnore
    private User user;
}
