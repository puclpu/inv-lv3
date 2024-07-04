package com.sparta.backoffice.teacher.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TeacherRequestDTO {

    @NotBlank(message = "강사 이름을 입력하지 않았습니다.")
    private String name;

    private int experience;

    @NotBlank(message = "회사를 입력하지 않았습니다.")
    private String company;

    @NotBlank(message = "전화번호를 입력하지 않았습니다.")
    private String phone;

    @NotBlank(message = "소개를 입력하지 않았습니다.")
    private String introduce;
}
