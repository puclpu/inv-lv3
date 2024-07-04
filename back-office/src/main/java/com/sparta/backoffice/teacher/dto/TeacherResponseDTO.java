package com.sparta.backoffice.teacher.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeacherResponseDTO {
    private String name;
    private int experience;
    private String company;
    private String phone;
    private String introduce;
}
