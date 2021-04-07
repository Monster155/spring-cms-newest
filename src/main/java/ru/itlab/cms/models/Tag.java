package ru.itlab.cms.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "tags")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
//@ToString
public class Tag {

    @ManyToMany(mappedBy = "tags")
    private List<Article> articles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Tag name can not be empty")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
