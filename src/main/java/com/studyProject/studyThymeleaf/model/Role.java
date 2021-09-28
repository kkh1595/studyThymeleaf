package com.studyProject.studyThymeleaf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String authority;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
