package com.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100)
    private String title;

    @Column(length = 100)
    private String author;

    @Column
    private int pagesNumber;

    @ManyToMany
    @JoinTable(name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id", referencedColumnName = "id"))
    private Set<Gender> genders;
}
