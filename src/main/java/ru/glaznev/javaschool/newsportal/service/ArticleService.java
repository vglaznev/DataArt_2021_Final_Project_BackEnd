package ru.glaznev.javaschool.newsportal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.glaznev.javaschool.newsportal.controller.dto.ArticleDTO;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;
import ru.glaznev.javaschool.newsportal.exception.ArticleNotFoundException;
import ru.glaznev.javaschool.newsportal.exception.InputFileException;
import ru.glaznev.javaschool.newsportal.repository.ArticleRepository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
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

    public List<ArticleDTO> getArticles() {
        return articleRepository.findAllByOrderByTimeDesc()
                .stream()
                .map(ArticleDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getArticlesByTopic(Topic topic){
        return articleRepository.findByTopicOrderByTimeDesc(topic)
                .stream()
                .map(ArticleDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    public ArticleDTO getArticleById(Long id) {
        return ArticleDTO.convertToDTO(articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article with id=" + id + " doesn't exist")));
    }

    public ArticleDTO uploadArticle(MultipartFile inputFile) {

        return null;
    }

    private ArticleDTO validateInputFile(MultipartFile inputFile) {
        /*if (!inputFile.getContentType().equals("application/x-zip-compressed")) {
            throw new InputFileException("Incorrect format");
        }
        try(ZipFile inputZipFile = new ZipFile((File) inputFile)){

        }catch (IOException exception) {

        }*/

        /*try (ZipInputStream zipInputStream = new ZipInputStream(inputFile.getInputStream())) {
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
        }*/
        //переделать под byte[], чтобы не использовать stream
        //задать значение размера byte[] под body
        return null;
    }

}
