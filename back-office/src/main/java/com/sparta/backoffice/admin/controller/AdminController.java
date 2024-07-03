package com.sparta.backoffice.admin.controller;

import com.sparta.backoffice.admin.dto.SignupRequestDTO;
import com.sparta.backoffice.admin.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody @Valid SignupRequestDTO requestDTO) {
        adminService.createAdmin(requestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
