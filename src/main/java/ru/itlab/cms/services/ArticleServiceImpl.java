package ru.itlab.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itlab.cms.models.Article;
import ru.itlab.cms.repository.ArticleRepo;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public boolean addArticle(Article article) {
        if (articleRepo.findArticle(article.getName(), article.getAuthor()) == null) {
            articleRepo.saveAndFlush(article);
            return true;
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        articleRepo.deleteById(id);
    }

    @Override
    public Article getText(Article article) {
        return articleRepo.findArticle(article.getName(), article.getAuthor());
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepo.findById(id).orElse(null);
    }

    @Override
    //returns old version of article
    public Article editText(Article article) {
        Article oldArticle = articleRepo.findArticle(article.getName(), article.getAuthor());
        articleRepo.saveAndFlush(article);
        return oldArticle;
    }

    @Override
    public List<Article> getAll() {
        return articleRepo.findAll();
    }
}
