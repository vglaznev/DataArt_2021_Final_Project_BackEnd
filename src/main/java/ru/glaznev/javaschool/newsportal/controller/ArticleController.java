package ru.glaznev.javaschool.newsportal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.dto.PageDTO;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;
import ru.glaznev.javaschool.newsportal.service.ArticleService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private static final int NUMBER_OF_ARTICLES_ON_PAGE = 2;

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles(
            @RequestParam(required = false) Topic topic,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "time", "id"));
        PageDTO responseBody = (topic == null ? articleService.getArticles(pageRequest) : articleService.getArticlesByTopic(topic, pageRequest));
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping("/upload/")
    public ResponseEntity<?> uploadArticle(@RequestParam Topic topic, @RequestPart("file") MultipartFile article) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(articleService.uploadArticle(topic, article));
    }

}
