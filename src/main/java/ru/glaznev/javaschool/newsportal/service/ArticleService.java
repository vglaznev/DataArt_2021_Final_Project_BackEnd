package ru.glaznev.javaschool.newsportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.exception.InputFileException;
import ru.glaznev.javaschool.newsportal.repository.ArticleRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleEntity> getArticles() {
        return articleRepository.getArticles();
    }

    public ArticleEntity getArticleById(Long id) {
        return articleRepository.getArticleById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id=" + id + " doesn't exist"));
    }

    public ArticleEntity uploadArticle(MultipartFile inputFile) {
        return articleRepository.createArticle(validateInputFile(inputFile));
    }

    private ArticleEntity validateInputFile(MultipartFile inputFile) {
        if (!inputFile.getContentType().equals("application/x-zip-compressed")) {
            throw new InputFileException("Incorrect format");
        }
        try (ZipInputStream zipInputStream = new ZipInputStream(inputFile.getInputStream())) {
            if (zipInputStream.getNextEntry() != null) {
                BufferedReader entryBuffer = new BufferedReader(new InputStreamReader(zipInputStream));
                String name = entryBuffer.readLine();
                if (zipInputStream.getNextEntry() != null) {
                    throw new InputFileException("Zip has more than 1 file");
                }
                if(!entryBuffer.ready()){
                    throw new InputFileException("Article has no body");
                }
                String body = entryBuffer.lines().collect(Collectors.joining(System.lineSeparator()));
                return new ArticleEntity(name, body);
            }
            throw new InputFileException("Empty Zip");
        } catch (IOException exception) {
            throw new InputFileException("Cannot open zip");
        }
    }

}
