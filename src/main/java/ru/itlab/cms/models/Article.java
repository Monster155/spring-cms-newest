package ru.itlab.cms.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "articles")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 30, message = "Article name must contain from 1 to 30 characters")
    @NotEmpty(message = "Article name can not be empty")
    private String name;
    @NotEmpty(message = "Author name can not be empty")
    private String author;
    @CreationTimestamp
//    @Column(insertable = false)
    private java.sql.Timestamp date;
    @Column(columnDefinition = "text")
    @NotEmpty(message = "Text can not be empty")
    private String text;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Tag> tags;

    public Article(String name, String author, String text, List<Tag> tags) {
        this.name = name;
        this.author = author;
        this.text = text;
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (tags != null) {
            tags.forEach(sb::append);
        }
        return name + ',' + author + ',' + date + ',' + text + ',' + sb.toString();
    }

    public String getURL() {
        return "name=" + name + "&author=" + author;
    }
}
