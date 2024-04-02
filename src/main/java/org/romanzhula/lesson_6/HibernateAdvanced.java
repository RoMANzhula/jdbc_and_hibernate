package org.romanzhula.lesson_6;

import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_6.entity.Student;


public class HibernateAdvanced {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (
                var sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession();
        ){
            session.beginTransaction();

//            var result = session.createQuery("select s from Student s", Student.class).list();
            var result = session.createQuery(
                    "select s from Student s join fetch s.enrollments", //query-fetch good solution for optimize query to DB
                    Student.class
            ).list();
            result.stream()
                    .map(Student::getEnrollments)
                    .forEach(System.out::println);

            session.getTransaction().commit();

        }
    }
}
