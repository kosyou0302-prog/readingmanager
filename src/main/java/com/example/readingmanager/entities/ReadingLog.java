package com.example.readingmanager.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="readlog")
@Getter
@Setter

public class ReadingLog {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(optional= false, fetch= FetchType.LAZY)
    @JoinColumn(name="work_id")
    private Work work;

    @Column
    @Past
    private LocalDate finished_date;

    @Column(length = 255)
    private String memo;

    @Column
    private Integer rating;
}
