package com.sparta.backoffice.lecture.repository;

import com.sparta.backoffice.lecture.domain.Lecture;
import com.sparta.backoffice.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findAllByTeacherOrderByCreatedAtDesc(Teacher teacher);
}
