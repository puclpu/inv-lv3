package com.sparta.backoffice.teacher.service;

import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.dto.TeacherRequestDTO;
import com.sparta.backoffice.teacher.dto.TeacherResponseDTO;
import com.sparta.backoffice.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Transactional
    public TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO) {
        Teacher teacher = Teacher.builder()
                .name(requestDTO.getName())
                .experience(requestDTO.getExperience())
                .company(requestDTO.getCompany())
                .phone(requestDTO.getPhone())
                .introduce(requestDTO.getIntroduce())
                .build();
        teacherRepository.save(teacher);
        return convertToTeacherResponseDTO(teacher);
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(TeacherRequestDTO requestDTO, Long teacherId) {
        // DB에 저장된 강사인지 확인
        Teacher teacher = findTeacher(teacherId);
        teacher.update(requestDTO);

        return convertToTeacherResponseDTO(teacher);
    }

    public TeacherResponseDTO readTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        return convertToTeacherResponseDTO(teacher);
    }

    private TeacherResponseDTO convertToTeacherResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .name(teacher.getName())
                .experience(teacher.getExperience())
                .company(teacher.getCompany())
                .phone(teacher.getPhone())
                .introduce(teacher.getIntroduce())
                .build();
    }

    private Teacher findTeacher(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new RuntimeException("해당하는 강사가 없습니다.");
        }
        return optionalTeacher.get();
    }
}
