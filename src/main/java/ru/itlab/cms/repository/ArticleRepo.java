package ru.itlab.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itlab.cms.models.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {
    @Query("select b from Article b where b.name = :name and b.author = :author")
    Article findArticle(@Param("name") String name, @Param("author") String author);
}
