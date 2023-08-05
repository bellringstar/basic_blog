package com.example.blog.converter;

import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleRequest;
import com.example.blog.dto.ArticleResponse;
import org.springframework.stereotype.Component;

@Component
public class ArticleConverter {

    public Article toEntity(ArticleRequest addArticleRequest){
        return Article.builder()
                .title(addArticleRequest.getTitle())
                .content(addArticleRequest.getContent())
                .build();
    }

    public ArticleResponse toResponse(Article article){
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }


}
