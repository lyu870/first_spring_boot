package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    @GeneratedValue
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}