package com.sparta.backoffice.lecture.dto;

import com.sparta.backoffice.lecture.type.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LectureRequestDTO {
    @NotBlank(message = "강의 이름을 입력하지 않았습니다.")
    private String name;

    private int price;

    @NotBlank(message = "강의 소개를 입력하지 않았습니다.")
    private String introduce;

    @NotNull(message = "강의 카테고리를 입력하지 않았습니다")
    private Category category;

    @NotNull(message = "강사를 입력하지 않았습니다.")
    private Long teacherId;
}
