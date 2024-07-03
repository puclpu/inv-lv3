package com.sparta.backoffice.teacher.controller;

import com.sparta.backoffice.admin.type.Role;
import com.sparta.backoffice.teacher.domain.Teacher;
import com.sparta.backoffice.teacher.dto.TeacherRequestDTO;
import com.sparta.backoffice.teacher.dto.TeacherResponseDTO;
import com.sparta.backoffice.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @Secured({Role.Authority.STAFF, Role.Authority.MANAGER})
    @PostMapping()
    public ResponseEntity<TeacherResponseDTO> createTeacher(@RequestBody TeacherRequestDTO requestDTO) {
        TeacherResponseDTO responseDTO = teacherService.createTeacher(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Secured(Role.Authority.MANAGER)
    @PutMapping("/{teacherId}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(@RequestBody TeacherRequestDTO requestDTO, @PathVariable Long teacherId) {
        TeacherResponseDTO responseDTO = teacherService.updateTeacher(requestDTO, teacherId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
