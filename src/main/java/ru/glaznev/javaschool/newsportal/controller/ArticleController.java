package ru.glaznev.javaschool.newsportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.service.ArticleService;

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

    @PostMapping("/upload/")
    public ResponseEntity<?> uploadArticle(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(/*articleService.uploadArticle(file)*/null);
    }
}
