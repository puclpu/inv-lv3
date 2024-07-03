package com.sparta.backoffice.teacher.service;

import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.dto.TeacherRequestDTO;
import com.sparta.backoffice.teacher.dto.TeacherResponseDTO;
import com.sparta.backoffice.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO) {
        Teacher teacher = Teacher.builder()
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .company(requestDTO.getCompany())
                .phone(requestDTO.getPhone())
                .introduce(requestDTO.getIntroduce())
                .build();
        teacherRepository.save(teacher);
        return TeacherResponseDTO.builder()
                .name(teacher.getName())
                .experience(teacher.getExperience())
                .company(teacher.getCompany())
                .phone(teacher.getPhone())
                .introduce(teacher.getIntroduce())
                .build();
    }
}
