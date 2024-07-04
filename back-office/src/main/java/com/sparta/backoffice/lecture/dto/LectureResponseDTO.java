package com.sparta.backoffice.lecture.dto;

import com.sparta.backoffice.lecture.type.Category;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class LectureResponseDTO {
    private String name;
    private int price;
    private String introduce;
    private Category category;
    private Long teacherId;
    private LocalDateTime createdAt;
}
