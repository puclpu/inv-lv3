package com.sparta.backoffice.lecture.domain;

import com.sparta.backoffice.lecture.dto.LectureRequestDTO;
import com.sparta.backoffice.lecture.type.Category;
import com.sparta.backoffice.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecture")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Lecture extends Timestamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "introduce", nullable = false, length = 150)
    private String introduce;

    @Column(name = "category", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public static Lecture of (LectureRequestDTO requestDTO, Teacher teacher) {
        return Lecture.builder()
                .name(requestDTO.getName())
                .price(requestDTO.getPrice())
                .introduce(requestDTO.getIntroduce())
                .category(requestDTO.getCategory())
                .teacher(teacher)
                .build();
    }

    public void update(LectureRequestDTO requestDTO) {
        this.name = requestDTO.getName();
        this.price = requestDTO.getPrice();
        this.introduce = requestDTO.getIntroduce();
        this.category = requestDTO.getCategory();
    }
}
