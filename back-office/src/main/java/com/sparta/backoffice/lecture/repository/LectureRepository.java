package com.sparta.backoffice.lecture.repository;

import com.sparta.backoffice.lecture.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
