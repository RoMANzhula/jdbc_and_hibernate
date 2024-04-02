package org.romanzhula.lesson_6.entity;

import jakarta.persistence.*;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import java.util.List;

@NamedQuery(name = "studentByCourse", query = "select s from Enrollment e join e.student s where e.course.name = :courseName")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "students", schema = "public")
//@OptimisticLocking(type = OptimisticLockType.VERSION)
//@OptimisticLocking(type = OptimisticLockType.DIRTY) //with OptimisticLockType.DIRTY we must use @DynamicUpdate
//@OptimisticLocking(type = OptimisticLockType.ALL)
//@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
//    @BatchSize(size = 3) // == N/3 + 1 (example:we have 90 students that - 90/3 + 1 = 31 query to the DB)
//    @Fetch(FetchMode.SUBSELECT) //this method help us have only 1 + 1 query to DB (but it's not best solution)
    private List<Enrollment> enrollments;

    //for OptimisticLockType.VERSION
//    @Version
//    private Integer version;

}
