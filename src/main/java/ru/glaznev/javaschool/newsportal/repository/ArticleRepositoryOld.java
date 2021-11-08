package ru.glaznev.javaschool.newsportal.repository;

import org.springframework.stereotype.Repository;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ArticleRepositoryOld {
    private Map<Long, ArticleEntity> database = new HashMap<>();
    @PostConstruct
    public void initRepository(){
    }

    public List<ArticleEntity> getArticles(){
        return new ArrayList<>(database.values());
    }

    public Optional<ArticleEntity> getArticleById(Long id){
        return Optional.ofNullable(database.get(id));
    }
    public ArticleEntity createArticle(ArticleEntity input){
        database.put((long)database.size(), input);
        return input;
    }
}
