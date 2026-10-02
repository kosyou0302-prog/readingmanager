package com.example.readingmanager.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="author")
@Getter
@Setter

public class Author {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(length=20)
    private String name;

    @Column
    @Past
    private LocalDate birth;

    @Column
    @Past
    private LocalDate death;
}
