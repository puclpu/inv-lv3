package com.sparta.backoffice.admin.validator;

import com.sparta.backoffice.admin.dto.SignupRequestDTO;
import com.sparta.backoffice.admin.type.Department;
import com.sparta.backoffice.admin.type.Role;
import com.sparta.backoffice.global.exception.CustomException;
import com.sparta.backoffice.global.exception.ExceptionCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManagerValidator implements ConstraintValidator<ValidManager, SignupRequestDTO> {

    @Override
    public void initialize(ValidManager constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SignupRequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        if (Role.MANAGER.equals(requestDTO.getRole())) {
            Department department = requestDTO.getDepartment();
            if (department.equals(Department.CURRICULUM) || department.equals(Department.DEVELOPMENT)) {
                return true;
            } else {
                log.error("CURRICULUM 또는 DEVELOPMENT 부서만 MANAGER 권한을 가질 수 있습니다.");
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("CURRICULUM 또는 DEVELOPMENT 부서만 MANAGER 권한을 가질 수 있습니다.")
                        .addConstraintViolation();
                return false;
            }
        }
        return false;
    }
}
