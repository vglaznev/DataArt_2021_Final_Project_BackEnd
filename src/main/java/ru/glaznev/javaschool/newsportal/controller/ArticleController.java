package ru.glaznev.javaschool.newsportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;
import ru.glaznev.javaschool.newsportal.service.ArticleService;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping( "/articles")
    public ResponseEntity<?> getArticles(){
        return ResponseEntity.ok(articleService.getArticles());
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @GetMapping("/articles/")
    public ResponseEntity<?> getArticlesByTopic(@RequestParam Topic topic){
        return ResponseEntity.ok(articleService.getArticlesByTopic(topic));
    }

    @PostMapping("/upload/")
    public ResponseEntity<?> uploadArticle(@RequestParam Topic topic, @RequestPart("file") MultipartFile article){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.uploadArticle(article));
    }

}
