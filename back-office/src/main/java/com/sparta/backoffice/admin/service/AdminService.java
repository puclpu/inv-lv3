package com.sparta.backoffice.admin.service;

import com.sparta.backoffice.admin.domain.Admin;
import com.sparta.backoffice.admin.dto.SignupRequestDTO;
import com.sparta.backoffice.admin.repository.AdminRepository;
import com.sparta.backoffice.global.exception.CustomException;
import com.sparta.backoffice.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void createAdmin(SignupRequestDTO requestDTO) {
        String email = requestDTO.getEmail();
        String password = passwordEncoder.encode(requestDTO.getPassword());

        // 회원 중복 확인
        if (adminRepository.existsByEmail(email)) {
            throw new CustomException(ExceptionCode.MEMBER_EXISTS);
        }

        // 사용자 등록
        Admin admin = Admin.builder()
                .email(email)
                .password(password)
                .department(requestDTO.getDepartment())
                .role(requestDTO.getRole())
                .build();
        adminRepository.save(admin);
    }
}
