package org.romanzhula.lesson_6;

import jakarta.persistence.LockModeType;
import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_6.entity.Student;


public class HibernateLocking {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

//        try (
//                var sessionFactory = configuration.buildSessionFactory();
//                var session = sessionFactory.openSession();
//                var session1 = sessionFactory.openSession()
//        ){
//            session.beginTransaction();
////            session1.beginTransaction(); //for example with OPTIMISTIC_FORCE_INCREMENT
//
////            var result = session.find(Student.class, 1, LockModeType.OPTIMISTIC);
////            result.setFirstName("Test locking.");
//
//
////            var result = session.find(Student.class, 1, LockModeType.OPTIMISTIC_FORCE_INCREMENT); //will be increment only version
////            result.setFirstName("Test locking with result");
//
////            var theSameStudent = session.find(Student.class, 1, LockModeType.OPTIMISTIC_FORCE_INCREMENT); //will be increment only version
////            theSameStudent.setFirstName("Test locking with theSameStudent");
//
//            //example for OptimisticLockType.DIRTY
////            var result = session.find(Student.class, 1);
//////            result.setFirstName("Test locking with result 5");
////            result.setLastName("Test locking with result 5");
//
//            //example for OptimisticLockType.DIRTY (here Hibernate use all fields from table
//            var result = session.find(Student.class, 1);
//            result.setFirstName("Test locking with result 6");
//            result.setLastName("Test locking with result 6");
//
//
//            session.getTransaction().commit();
////            session1.getTransaction().commit(); //for example with OPTIMISTIC_FORCE_INCREMENT
//        }

                //example with PESSIMISTIC
        try (
                var sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession();
                var session1 = sessionFactory.openSession()
        ){
            session.beginTransaction();
            session1.beginTransaction(); //for example with OPTIMISTIC_FORCE_INCREMENT

//            var result = session.find(Student.class, 1, LockModeType.PESSIMISTIC_READ);
//            result.setFirstName("Test locking with result 7");
//
//            var theSameStudent = session.find(Student.class, 1, LockModeType.PESSIMISTIC_READ);
//            theSameStudent.setFirstName("Test locking with theSameStudent 7");

//            var result = session.find(Student.class, 1, LockModeType.PESSIMISTIC_WRITE);
//            result.setFirstName("Test locking with result 8");
//
//            var theSameStudent = session.find(Student.class, 1, LockModeType.PESSIMISTIC_WRITE);
//            theSameStudent.setFirstName("Test locking with theSameStudent 8");

            var result = session.find(Student.class, 1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            result.setFirstName("Test locking with result 9");

            var theSameStudent = session.find(Student.class, 1, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
            theSameStudent.setFirstName("Test locking with theSameStudent 9");


            session.getTransaction().commit();
            session1.getTransaction().commit();
        }
    }
}
