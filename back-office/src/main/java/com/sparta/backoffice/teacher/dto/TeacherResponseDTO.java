package com.sparta.backoffice.teacher.dto;

import com.sparta.backoffice.teacher.domain.Teacher;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TeacherResponseDTO {
    private String name;
    private int experience;
    private String company;
    private String phone;
    private String introduce;

    public static TeacherResponseDTO from(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .name(teacher.getName())
                .experience(teacher.getExperience())
                .company(teacher.getCompany())
                .phone(teacher.getPhone())
                .introduce(teacher.getIntroduce())
                .build();
    }
}
