package org.romanzhula.lesson_4;


import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_4.entity.Course;
import org.romanzhula.lesson_4.entity.Enrollment;
import org.romanzhula.lesson_4.entity.Student;

import java.time.LocalDate;

public class HibernateMapping {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (
                var sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession()
        ){
            session.beginTransaction();

//            var student = Student.builder()
//                    .id(2)
//                    .firstName("Test XML mapping")
//                    .lastName("Test XML mapping")
//                    .email("test_xml_mapping@test.test")
//                    .build();
//
//            session.save(student);


//            var student = Student.builder()
//                    .id(3)
//                    .firstName("Test for course")
//                    .lastName("Test for course")
//                    .email("course@test.test")
//                    .build();
//
//            var course = Course.builder()
//                    .id(1)
//                    .name("Java Start")
//                    .description("Introduction to Java")
//                    .duration(20)
//                    .build();
//
//            var enrollment = Enrollment.builder()
//                    .id(1)
//                    .student(student)
//                    .course(course)
//                    .registrationDate(LocalDate.of(2024, 4, 1))
//                    .build();
//
//            session.save(student);
//            session.save(course);
//            session.save(enrollment);


//            var course = Course.builder()
//                    .id(2)
//                    .name("JDBC Start")
//                    .description("Introduction to JDBC")
//                    .duration(40)
//                    .build();

//            Student studentFromDb = session.find(Student.class, 3);
//            Enrollment enrollmentFromDb = session.find(Enrollment.class, 3);
//
//            var enrollment = Enrollment.builder()
//                    .id(1)
//                    .student(studentFromDb)
//                    .course(course)
//                    .registrationDate(LocalDate.of(2024, 4, 1))
//                    .build();
//
//            session.save(course);
//            session.save(enrollment);

//            Student studentFromDb = session.find(Student.class, 3);
//
//            studentFromDb.getEnrollments().stream().map(
//                    e -> e
//                            .getCourse()
//                            .getName())
//                    .forEach(System.out::println);


            var student = Student.builder()
                    .firstName("Test for IDENTITY id")
                    .lastName("Test for IDENTITY id")
                    .email("identity@test.test")
                    .build();

            var course = Course.builder()
                    .name("Java Start IDENTITY id")
                    .description("Introduction to Java with IDENTITY id")
                    .duration(20)
                    .build();

            var enrollment = Enrollment.builder()
                    .student(student)
                    .course(course)
                    .registrationDate(LocalDate.of(2024, 4, 1))
                    .build();

            session.save(student);
            session.save(course);
            session.save(enrollment);


            session.getTransaction().commit();
        }
    }
}
