package ru.itlab.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.itlab.cms.models.Article;
import ru.itlab.cms.services.ArticleServiceImpl;
import ru.itlab.cms.services.TagServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private TagServiceImpl tagService;

    @GetMapping
    public String get() {
        // TODO Add security
        return "control";
    }

    @PostMapping(value = "{id}")
    @ResponseBody
    public boolean post(@PathVariable(name = "id") int id) {
        articleService.delete(id);
        return true;
    }

    @GetMapping("/table/{pageID}")
    public ResponseEntity<List<Article>> getTable(@PathVariable(name = "pageID") int pageID) {
        List<Article> articles = articleService.getAll();
        System.out.println("page id " + pageID);
        if (articles != null) {
            int articlesOnPage = 7;
            int firstIndex = pageID * articlesOnPage;
            System.out.println("Results: " + (firstIndex > articles.size() - articlesOnPage) + " " + (firstIndex < 0) + " " + (firstIndex + articlesOnPage > articles.size()));
            if (firstIndex > articles.size())
                articles = new ArrayList<>();
            else {
                if (firstIndex < 0)
                    firstIndex = 0;
                if (firstIndex + articlesOnPage > articles.size())
                    articlesOnPage = articles.size() - firstIndex;
                articles = articles.subList(firstIndex, firstIndex + articlesOnPage);
            }
        }

        if (articles == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (articles.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
