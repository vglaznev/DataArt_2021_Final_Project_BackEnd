package ru.glaznev.javaschool.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;
import ru.glaznev.javaschool.newsportal.enumeration.Topic;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByOrderByTimeDesc();
    List<ArticleEntity> findByTopicOrderByTimeDesc(Topic topic);
}

