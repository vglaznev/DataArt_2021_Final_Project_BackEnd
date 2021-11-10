package ru.glaznev.javaschool.newsportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    List<ArticleDTO> listOfArticles;
    int totalNumberOfPages;
    int numberOfCurrentPage;
    boolean hasPrevious;
    boolean hasNext;
    boolean isFirstPage;
    boolean isLastPage;


    public static PageDTO of(Page<ArticleEntity> articleEntityPage){
        return PageDTO.builder()
                .listOfArticles(articleEntityPage.get().map(ArticleDTO::of).toList())
                .totalNumberOfPages(articleEntityPage.getTotalPages())
                .numberOfCurrentPage(articleEntityPage.getNumber())
                .hasNext(articleEntityPage.hasNext())
                .hasPrevious(articleEntityPage.hasPrevious())
                .isFirstPage(articleEntityPage.isFirst())
                .isLastPage(articleEntityPage.isLast())
                .build();
    }
}
