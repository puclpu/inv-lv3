package com.sparta.backoffice.lecture.service;

import com.sparta.backoffice.lecture.domain.Lecture;
import com.sparta.backoffice.lecture.dto.LectureRequestDTO;
import com.sparta.backoffice.lecture.dto.LectureResponseDTO;
import com.sparta.backoffice.lecture.repository.LectureRepository;
import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    @Transactional
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

    @Transactional
    public LectureResponseDTO updateLecture(LectureRequestDTO requestDTO, Long lectureId) {
        Lecture lecture = findLecture(lectureId);
        lecture.update(requestDTO);

        return convertToLectureResponseDTO(lecture);
    }

    public LectureResponseDTO readLecture(Long lectureId) {
        Lecture lecture = findLecture(lectureId);
        return convertToLectureResponseDTO(lecture);
    }

    public List<LectureResponseDTO> readLectureByTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        List<Lecture> lectureList = lectureRepository.findAllByTeacherOrderByCreatedAtDesc(teacher);
        List<LectureResponseDTO> responseDTOList = new ArrayList<>();
        for (Lecture lecture : lectureList) {
            responseDTOList.add(convertToLectureResponseDTO(lecture));
        }
        return responseDTOList;
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

    private Lecture findLecture(Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty()) {
            throw new RuntimeException("해당 강의는 존재하지 않습니다.");
        }
        return optionalLecture.get();
    }

    private Teacher findTeacher(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new RuntimeException("해당 강사는 존재하지 않습니다.");
        }
        return optionalTeacher.get();
    }
}
