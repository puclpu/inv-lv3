package com.sparta.backoffice.teacher.service;

import com.sparta.backoffice.global.exception.CustomException;
import com.sparta.backoffice.global.exception.ExceptionCode;
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
        Teacher teacher = Teacher.from(requestDTO);
        teacherRepository.save(teacher);
        return TeacherResponseDTO.from(teacher);
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(TeacherRequestDTO requestDTO, Long teacherId) {
        // DB에 저장된 강사인지 확인
        Teacher teacher = findTeacher(teacherId);
        teacher.update(requestDTO);

        return TeacherResponseDTO.from(teacher);
    }

    public TeacherResponseDTO readTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        return TeacherResponseDTO.from(teacher);
    }

    @Transactional
    public void deleteTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        teacherRepository.delete(teacher);
    }

    private Teacher findTeacher(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new CustomException(ExceptionCode.TEACHER_NOT_FOUND);
        }
        return optionalTeacher.get();
    }
}
