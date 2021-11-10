package ru.glaznev.javaschool.newsportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.controller.dto.ArticleDTO;
import ru.glaznev.javaschool.newsportal.controller.dto.PageDTO;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.exception.InputFileException;
import ru.glaznev.javaschool.newsportal.repository.ArticleRepository;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @PostConstruct
    public void addSomeData(){
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_1")
                        .topic(Topic.POLITICS)
                        .body("Some body_1")
                        .build());
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_2")
                        .topic(Topic.POLITICS)
                        .body("Some body")
                        .build());
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_3")
                        .topic(Topic.POLITICS)
                        .body("Some body")
                        .build());
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_4")
                        .topic(Topic.FINANCE)
                        .body("Some body")
                        .build());
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_5")
                        .topic(Topic.FINANCE)
                        .body("Some body")
                        .build());
        articleRepository.save(
                ArticleEntity.builder()
                        .title("Title_6")
                        .topic(Topic.SCIENCE)
                        .body("Some body")
                        .build());
    }

    public PageDTO getArticles(PageRequest pageRequest) {
        return PageDTO.of(articleRepository.findAll(pageRequest));
    }

    public PageDTO getArticlesByTopic(Topic topic, Pageable pageable){
        return PageDTO.of(articleRepository.findAllByTopic(topic, pageable));
    }

    public ArticleDTO getArticleById(Long id) {
        return ArticleDTO.of(articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id=" + id + " doesn't exist")));
    }

    public ArticleDTO uploadArticle(Topic topic, MultipartFile inputFile) {

        return null;
    }

    private ArticleEntity createArticle(Topic topic, MultipartFile inputFile){
        String title;
        String body;

        if (!inputFile.getContentType().equals("application/x-zip-compressed")) {
            throw new InputFileException("Incorrect format");
        }

        try(ZipInputStream zipInputStream = new ZipInputStream(inputFile.getInputStream())){
            Optional<ZipEntry> optionalZipEntry= Optional.ofNullable(zipInputStream.getNextEntry());


        }catch(IOException ioException){

        }


        return  ArticleEntity.builder()
                .title(title)
                .topic(topic)
                .body(body)
                .build();
    }

    private boolean validateFile(MultipartFile inputFile){
        return false;
    }



}
