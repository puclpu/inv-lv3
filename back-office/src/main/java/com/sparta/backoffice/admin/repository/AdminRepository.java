package com.sparta.backoffice.admin.repository;

import com.sparta.backoffice.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
}
