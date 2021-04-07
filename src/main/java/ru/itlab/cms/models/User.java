package ru.itlab.cms.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 30, message = "Email must contain from 1 to 30 characters")
    @NotEmpty(message = "Email can not be empty")
    private String email;
    @Size(min = 8, max = 30, message = "Password must contain from 1 to 30 characters")
    @NotEmpty(message = "Password can not be empty")
    private String password;
}
