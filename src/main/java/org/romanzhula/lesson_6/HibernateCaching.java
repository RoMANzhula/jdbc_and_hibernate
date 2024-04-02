package org.romanzhula.lesson_6;

import jakarta.persistence.LockModeType;
import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_6.entity.Student;


public class HibernateCaching {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        try (
                var sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession();
                var session1 = sessionFactory.openSession()
        ){
            session.beginTransaction();
            session1.beginTransaction(); //for example with OPTIMISTIC_FORCE_INCREMENT

            //here Hibernate make only 1 query to DB because it use cache
            session.find(Student.class, 1);
            session.find(Student.class, 1);

            //here Hibernate make only 1 query to DB because it use cache
            session1.find(Student.class, 1);
            session1.find(Student.class, 1);

            session.getTransaction().commit();
            session1.getTransaction().commit();
        }
    }
}
