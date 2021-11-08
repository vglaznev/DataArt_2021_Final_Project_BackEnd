package ru.glaznev.javaschool.newsportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.glaznev.javaschool.newsportal.entity.ArticleEntity;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAllByOrderByTimeDesc();
    List<ArticleEntity> findByTopicOrderByTimeDesc(String topic);
}

