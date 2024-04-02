package org.romanzhula.lesson_5.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NamedQuery(name = "studentByCourse", query = "select s from Enrollment e join e.student s where e.course.name = :courseName")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Enrollment> enrollments;

}
