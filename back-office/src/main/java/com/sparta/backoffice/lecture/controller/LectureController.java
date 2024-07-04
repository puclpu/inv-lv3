package com.sparta.backoffice.lecture.controller;

import com.sparta.backoffice.admin.type.Role;
import com.sparta.backoffice.lecture.dto.LectureRequestDTO;
import com.sparta.backoffice.lecture.dto.LectureResponseDTO;
import com.sparta.backoffice.lecture.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/lecture")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @Secured({Role.Authority.STAFF, Role.Authority.MANAGER})
    @PostMapping()
    public ResponseEntity<LectureResponseDTO> createLecture(@RequestBody @Valid LectureRequestDTO requestDTO) {
        LectureResponseDTO responseDTO = lectureService.createLecture(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Secured(Role.Authority.MANAGER)
    @PutMapping("/{lectureId}")
    public ResponseEntity<LectureResponseDTO> updateLecture(@RequestBody @Valid LectureRequestDTO requestDTO, @PathVariable Long lectureId) {
        LectureResponseDTO responseDTO = lectureService.updateLecture(requestDTO, lectureId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @Secured({Role.Authority.STAFF, Role.Authority.MANAGER})
    @GetMapping("/{lectureId}")
    public ResponseEntity<LectureResponseDTO> readLecture(@PathVariable Long lectureId) {
        LectureResponseDTO responseDTO = lectureService.readLecture(lectureId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
