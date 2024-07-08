package com.sparta.backoffice.lecture.dto;

import com.sparta.backoffice.lecture.domain.Lecture;
import com.sparta.backoffice.lecture.type.Category;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureResponseDTO {
    private String name;
    private int price;
    private String introduce;
    private Category category;
    private Long teacherId;
    private LocalDateTime createdAt;

    public static LectureResponseDTO from(Lecture lecture) {
        return LectureResponseDTO.builder()
                .name(lecture.getName())
                .price(lecture.getPrice())
                .introduce(lecture.getIntroduce())
                .category(lecture.getCategory())
                .teacherId(lecture.getTeacher().getId())
                .createdAt(lecture.getCreatedAt())
                .build();
    }
}
