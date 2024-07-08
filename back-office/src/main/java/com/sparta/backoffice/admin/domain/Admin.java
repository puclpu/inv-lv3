package com.sparta.backoffice.admin.domain;

import com.sparta.backoffice.admin.dto.SignupRequestDTO;
import com.sparta.backoffice.admin.type.Department;
import com.sparta.backoffice.admin.type.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admin")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true,length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "department", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "role", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Admin of(SignupRequestDTO requestDTO, String password) {
        return Admin.builder()
                .email(requestDTO.getEmail())
                .password(password)
                .department(requestDTO.getDepartment())
                .role(requestDTO.getRole())
                .build();
    }

}
