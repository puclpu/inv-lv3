package com.sparta.backoffice.teacher.repository;

import com.sparta.backoffice.teacher.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
