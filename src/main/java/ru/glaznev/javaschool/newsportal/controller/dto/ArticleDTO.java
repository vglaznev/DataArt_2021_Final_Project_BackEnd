package ru.glaznev.javaschool.newsportal.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    Long id;
    String title;
    String topic;
    String body;
    Timestamp uploadTime;

    public static ArticleDTO convertToDTO(ArticleEntity articleEntity){
        return new ArticleDTOBuilder()
                .id(articleEntity.getId())
                .title(articleEntity.getTitle())
                .topic(articleEntity.getTopic())
                .body(articleEntity.getBody())
                .uploadTime(articleEntity.getTime())
                .build();
    }
}