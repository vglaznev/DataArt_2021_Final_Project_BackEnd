package ru.glaznev.javaschool.newsportal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleEntity {
    Long id;
    String name;
    String topic;
    String body;
}
