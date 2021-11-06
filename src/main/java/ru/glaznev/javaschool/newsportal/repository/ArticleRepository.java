package ru.glaznev.javaschool.newsportal.repository;

import org.springframework.stereotype.Repository;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ArticleRepository {
    private Map<Long, ArticleEntity> database = new HashMap<>();

    @PostConstruct
    public void initRepository(){
        for(long counter = 0; counter < 5; counter++) {
            database.put(counter, ArticleEntity.builder()
                    .id(counter)
                    .name("Article " + counter)
                    .topic("Sport")
                    .body("Somebody")
                    .build());
        }
        for(long counter = 5; counter < 10; counter++) {
                database.put(counter, ArticleEntity.builder()
                        .id(counter)
                        .name("Article " + counter)
                        .topic("Photo")
                        .body("Another some body")
                        .build());
        }

    }

    public List<ArticleEntity> getArticles(){
        return new ArrayList<>(database.values());
    }

    public Optional<ArticleEntity> getArticleById(Long id){
        return Optional.ofNullable(database.get(id));
    }
}
