package com.sparta.backoffice.teacher.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "experience", nullable = false)
    private int experience;

    @Column(name = "company", nullable = false, length = 50)
    private String company;

    @Column(name = "phone", unique = true, nullable = false, length = 15)
    private String phone;

    @Column(name = "introduce", nullable = false, length = 150)
    private String introduce;
}
