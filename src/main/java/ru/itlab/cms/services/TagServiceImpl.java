package ru.itlab.cms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itlab.cms.models.Article;
import ru.itlab.cms.models.Tag;
import ru.itlab.cms.repository.TagRepo;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo tagRepo;

    @Override
    public List<Article> getArticles(Tag tag) {
        return null;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepo.findAll();
    }
}
