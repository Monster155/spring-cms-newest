package ru.itlab.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itlab.cms.models.Article;
import ru.itlab.cms.services.ArticleServiceImpl;
import ru.itlab.cms.services.TagServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/articlesAPI")
public class APIController {
    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private TagServiceImpl tagService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Article article) {
        articleService.addArticle(article);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Article>> read() {
        final List<Article> articles = articleService.getAll();

        return articles != null && !articles.isEmpty()
                ? new ResponseEntity<>(articles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Article> read(@PathVariable(name = "id") int id) {
        final Article article = articleService.getArticleById(id);

        return article != null
                ? new ResponseEntity<>(article, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Article> readParam(@RequestParam("id") int id) {
        final Article article = articleService.getArticleById(id);

        return article != null
                ? new ResponseEntity<>(article, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Article article) {
        /*final boolean updated = */
        articleService.editText(article);

//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        /*final boolean deleted =*/
        articleService.delete(id);

//        return deleted
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
