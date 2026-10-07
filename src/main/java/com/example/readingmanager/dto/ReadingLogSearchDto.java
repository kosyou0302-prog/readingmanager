package com.example.readingmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadingLogSearchDto {
    private String title;
    private Long authorId;
    private Integer readStatus;
}
