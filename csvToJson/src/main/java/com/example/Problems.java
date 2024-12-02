package com.example;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Problems {
    private String problemNumber;
    private String name;
    private String difficulty;
    private String Url;
    private Set<String> companies= new HashSet<>();

    public Problems(String id, String name, String difficulty, String url){
        problemNumber=id;
        this.name=name;
        this.difficulty= difficulty;
        this.Url= url;
    }

}
