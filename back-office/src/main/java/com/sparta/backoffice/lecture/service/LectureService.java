package com.sparta.backoffice.lecture.service;

import com.sparta.backoffice.lecture.domain.Lecture;
import com.sparta.backoffice.lecture.dto.LectureRequestDTO;
import com.sparta.backoffice.lecture.dto.LectureResponseDTO;
import com.sparta.backoffice.lecture.repository.LectureRepository;
import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    public LectureResponseDTO createLecture(LectureRequestDTO requestDTO) {
        Teacher teacher = findTeacher(requestDTO.getTeacherId());

        Lecture lecture = Lecture.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .introduce(requestDTO.getIntroduce())
                .category(requestDTO.getCategory())
                .teacher(teacher)
                .build();

        lectureRepository.save(lecture);
        return convertToLectureResponseDTO(lecture);
    }

    private LectureResponseDTO convertToLectureResponseDTO(Lecture lecture) {
        return LectureResponseDTO.builder()
                .name(lecture.getName())
                .price(lecture.getPrice())
                .introduce(lecture.getIntroduce())
                .category(lecture.getCategory())
                .teacherId(lecture.getTeacher().getId())
                .createdAt(lecture.getCreatedAt())
                .build();
    }

    private Teacher findTeacher(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new RuntimeException("해당 강사는 존재하지 않습니다.");
        }
        return optionalTeacher.get();
    }
}
