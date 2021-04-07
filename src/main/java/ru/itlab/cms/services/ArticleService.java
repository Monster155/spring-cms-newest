package ru.itlab.cms.services;

import ru.itlab.cms.models.Article;

import java.util.List;

public interface ArticleService {
    boolean addArticle(Article article);

    void delete(Integer id);

    Article getText(Article article);

    Article getArticleById(Integer id);

    Article editText(Article article);

    List<Article> getAll();
}
