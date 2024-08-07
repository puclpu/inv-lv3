package com.sparta.backoffice.lecture.service;

import com.sparta.backoffice.global.exception.CustomException;
import com.sparta.backoffice.global.exception.ExceptionCode;
import com.sparta.backoffice.lecture.domain.Lecture;
import com.sparta.backoffice.lecture.dto.LectureRequestDTO;
import com.sparta.backoffice.lecture.dto.LectureResponseDTO;
import com.sparta.backoffice.lecture.repository.LectureRepository;
import com.sparta.backoffice.lecture.type.Category;
import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TeacherRepository teacherRepository;

    public LectureResponseDTO createLecture(LectureRequestDTO requestDTO) {
        Teacher teacher = findTeacher(requestDTO.getTeacherId());

        Lecture lecture = Lecture.of(requestDTO, teacher);

        lectureRepository.save(lecture);
        return LectureResponseDTO.from(lecture);
    }

    @Transactional
    public LectureResponseDTO updateLecture(LectureRequestDTO requestDTO, Long lectureId) {
        Lecture lecture = findLecture(lectureId);
        lecture.update(requestDTO);

        return LectureResponseDTO.from(lecture);
    }

    public LectureResponseDTO readLecture(Long lectureId) {
        Lecture lecture = findLecture(lectureId);
        return LectureResponseDTO.from(lecture);
    }

    public List<LectureResponseDTO> readLectureByTeacher(Long teacherId) {
        Teacher teacher = findTeacher(teacherId);
        List<Lecture> lectureList = lectureRepository.findAllByTeacherOrderByCreatedAtDesc(teacher);
        List<LectureResponseDTO> responseDTOList = new ArrayList<>();
        for (Lecture lecture : lectureList) {
            responseDTOList.add(LectureResponseDTO.from(lecture));
        }
        return responseDTOList;
    }

    public List<LectureResponseDTO> readLectureByCategory(Category category) {
        List<Lecture> lectureList = lectureRepository.findAllByCategoryOrderByCreatedAtDesc(category);
        List<LectureResponseDTO> responseDTOList = new ArrayList<>();
        for (Lecture lecture : lectureList) {
            responseDTOList.add(LectureResponseDTO.from(lecture));
        }
        return responseDTOList;
    }

    public void deleteLecture(Long lectureId) {
        Lecture lecture = findLecture(lectureId);
        lectureRepository.delete(lecture);
    }

    private Lecture findLecture(Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        if (optionalLecture.isEmpty()) {
            throw new CustomException(ExceptionCode.LECTURE_NOT_FOUND);
        }
        return optionalLecture.get();
    }

    private Teacher findTeacher(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isEmpty()) {
            throw new CustomException(ExceptionCode.TEACHER_NOT_FOUND);
        }
        return optionalTeacher.get();
    }
}
