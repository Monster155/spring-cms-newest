package ru.itlab.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itlab.cms.models.Tag;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    @Query("select b from Tag b where b.id = :id")
    Tag getTag(@Param("id") Integer id);
}
