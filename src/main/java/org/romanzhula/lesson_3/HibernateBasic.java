package org.romanzhula.lesson_3;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_3.entity.Student;

public class HibernateBasic {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure(); //without parameter because hibernate.cfg.xml in package "resources" (in other case we must add all path to this file)

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession();
            ) {
//            System.out.println("OK! Session was created.");

            session.beginTransaction(); //manual activate transaction

            Student student = Student.builder()
                    .id(9)
                    .firstName("Testing hibernate 9")
                    .lastName("Testing hibernate 9")
                    .email("test@test.hibernate")
                    .build();
            session.save(student);

            session.getTransaction().commit();

        }
    }
}
