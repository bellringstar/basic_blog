package com.example.blog.service;

import com.example.blog.converter.ArticleConverter;
import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleRequest;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final ArticleConverter articleConverter;

    public Article save(ArticleRequest request){
        return blogRepository.save(articleConverter.toEntity(request));
    }

    public List<ArticleResponse> findAll(){
        return blogRepository.findAll().stream().map(articleConverter::toResponse).collect(Collectors.toList());
    }

    public ArticleResponse findById(Long id){
        return blogRepository.findById(id)
                .map(articleConverter::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+ id));
    }

    public void delete(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public ArticleResponse update(Long id, ArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+ id));

        article.update(request.getTitle(), request.getContent());

        return articleConverter.toResponse(article);
    }
}
