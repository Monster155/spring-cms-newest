package ru.itlab.cms.services;

import ru.itlab.cms.models.Article;
import ru.itlab.cms.models.Tag;

import java.util.List;

public interface TagService {
    List<Article> getArticles(Tag tag);

    List<Tag> getAll();
}
