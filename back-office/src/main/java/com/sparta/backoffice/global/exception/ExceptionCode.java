package com.sparta.backoffice.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {
    MANAGER_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "커리큘럼 또는 개발 부서만 MANAGER 권한을 가질 수 있습니다."),
    MEMBER_EXISTS(HttpStatus.CONFLICT, "해당 이메일의 회원이 이미 존재합니다."),
    TEACHER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강사는 존재하지 않습니다."),
    LECTURE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 강의는 존재하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
