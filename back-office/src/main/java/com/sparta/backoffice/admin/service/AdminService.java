package com.sparta.backoffice.admin.service;

import com.sparta.backoffice.admin.domain.Admin;
import com.sparta.backoffice.admin.dto.SignupRequestDTO;
import com.sparta.backoffice.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public void createAdmin(SignupRequestDTO requestDTO) {
        String email = requestDTO.getEmail();
        String password = passwordEncoder.encode(requestDTO.getPassword());

        // 회원 중복 확인
        Optional<Admin> checkEmail = adminRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다");
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
