package org.romanzhula.lesson_6.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "enrollment", schema = "public")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) //standart for annotation MtO is EAGER
    @JoinColumn(name = "student_id", nullable = false)
    @ToString.Exclude //make our code without loop (java.lang.StackOverflowError)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @ToString.Exclude
    private Course course;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

}
