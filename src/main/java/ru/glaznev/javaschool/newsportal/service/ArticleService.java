package ru.glaznev.javaschool.newsportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.dto.ArticleDTO;
import ru.glaznev.javaschool.newsportal.dto.PageDTO;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.exception.InputFileException;
import ru.glaznev.javaschool.newsportal.repository.ArticleRepository;

import javax.annotation.PostConstruct;
import javax.lang.model.type.ErrorType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @PostConstruct
    public void addSomeData() {
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

    public PageDTO getArticlesByTopic(Topic topic, Pageable pageable) {
        return PageDTO.of(articleRepository.findAllByTopic(topic, pageable));
    }

    public ArticleDTO getArticleById(Long id) {
        return ArticleDTO.of(articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id=" + id + " doesn't exist")));
    }

    public ArticleDTO uploadArticle(Topic topic, MultipartFile inputFile) {
        ArticleEntity articleEntity;
        validateInputFile(inputFile.getOriginalFilename());
        articleEntity = articleRepository.save(createArticle(topic, inputFile));

        return ArticleDTO.of(articleEntity);
    }

    private boolean validateInputFile(String inputFileName) {
        if (!inputFileName.substring(inputFileName.length() - 4).equals(".zip"))
            throw new InputFileException("Please, make sure you attach a ZIP-archive");
        return true;
    }

    private ArticleEntity createArticle(Topic topic, MultipartFile inputFile) {
        String title;
        String body;
        try (ZipInputStream zipInputStream = new ZipInputStream(inputFile.getInputStream())) {
            ZipEntry articleTxtEntry = zipInputStream.getNextEntry();
            if (articleTxtEntry == null) {
                throw new InputFileException("Empty Zip");
            }
            BufferedReader entryBuffer = new BufferedReader(new InputStreamReader(zipInputStream));
            title = entryBuffer.readLine();
            if(!articleTxtEntry.getName().equals("article.txt") || zipInputStream.getNextEntry() != null){
                throw new InputFileException("ZIP-archive should contain only 'article.txt'");
            }
            if (!entryBuffer.ready()) {
                throw new InputFileException("Article has no body");
            }
            body = entryBuffer
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            return ArticleEntity.builder()
                    .title(title)
                    .topic(topic)
                    .body(body)
                    .build();

        } catch (IOException exception) {
            throw new InputFileException("Cannot open zip");

        }
    }
}
