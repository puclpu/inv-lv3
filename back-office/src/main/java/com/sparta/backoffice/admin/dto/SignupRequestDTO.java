package com.sparta.backoffice.admin.dto;

import com.sparta.backoffice.admin.type.Department;
import com.sparta.backoffice.admin.type.Role;
import com.sparta.backoffice.admin.validator.ValidManager;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
@ValidManager
public class SignupRequestDTO {

    @Email(message = "이메일 형식으로 입력하세요.")
    @NotBlank(message = "값이 입력되지 않았습니다")
    private String email;

    @Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\\W])).{8,15}+$")
    @NotBlank(message = "값이 입력되지 않았습니다")
    private String password;

    @NotNull(message = "값이 입력되지 않았습니다")
    private Department department;

    @NotNull(message = "값이 입력되지 않았습니다")
    private Role role;

}
