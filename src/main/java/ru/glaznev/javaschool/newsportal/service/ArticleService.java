package ru.glaznev.javaschool.newsportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.repository.ArticleRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleEntity> getArticles(){
        return articleRepository.getArticles();
    }

    public ArticleEntity getArticleById(Long id){
        return articleRepository.getArticleById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id=" + id + " doesn't exist"));
    }


}
