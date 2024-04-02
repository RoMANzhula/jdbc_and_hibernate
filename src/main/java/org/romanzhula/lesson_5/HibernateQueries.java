package org.romanzhula.lesson_5;

import org.hibernate.cfg.Configuration;
import org.romanzhula.lesson_5.entity.Student;

public class HibernateQueries {
    public static void main(String[] args) {
        var configuration = new Configuration();
        configuration.configure();

        //pagination
//        int pageNumber = 1; //we'll have result - 2 students with id 5 nd 4
        int pageNumber = 2; //we'll have result - 2 students with id 3 nd 2
        int pageSize = 2;

//        Student student;

//        try (
//                var sessionFactory = configuration.buildSessionFactory();
//                var session = sessionFactory.openSession()
//        ){
//            session.beginTransaction();
//
//            student = session.find(Student.class, 1);
//
//            session.getTransaction().commit();
//
//            System.out.println(student.getFirstName());
//            System.out.println(student.getLastName());
//            System.out.println(student.getEnrollments().size());
//
//        }

//        String hql = "select s from Student s where id = ?1";
//        String hql = "select s from Student s where id = :id";
//
//        try (
//                var sessionFactory = configuration.buildSessionFactory();
//                var session = sessionFactory.openSession()
//        ){
//            session.beginTransaction();
//
////            var result = session.createQuery(hql, Student.class).setParameter(1, 1).list();
//            var result = session.createQuery(hql, Student.class).setParameter("id", 1).list();
//
//            result.forEach(System.out::println);
//
//            session.getTransaction().commit();
//
//        }

//        String course = "JDBC Basic";
//
//        String hql = "select s from Enrollment e join e.student s where e.course.name = :courseName";
//
//        try (
//                var sessionFactory = configuration.buildSessionFactory();
//                var session = sessionFactory.openSession()
//        ){
//            session.beginTransaction();
//
////            var result = session.createQuery(hql, Student.class).setParameter(1, 1).list();
//            var result = session
//                    .createNamedQuery("studentByCourse", Student.class)
//                    .setParameter("courseName", course).list();
//
//            result.forEach(System.out::println);
//
//            session.getTransaction().commit();
//
//        }


        String course = "JDBC Basic";

        String hql = "select s from Enrollment e join e.student s where e.course.name = :courseName";

        try (
                var sessionFactory = configuration.buildSessionFactory();
                var session = sessionFactory.openSession()
        ){
            session.beginTransaction();

            var cb = session.getCriteriaBuilder();
            var criteria = cb.createQuery(Student.class);

            var student = criteria.from(Student.class);

//            criteria.select(student).where(
//                    cb.equal(student.get("id"), 1)
//            );

            criteria.select(student).orderBy(
                    cb.desc(student.get("id"))
            );


            var result = session.createQuery(criteria)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();


            result.forEach(System.out::println);

            session.getTransaction().commit();

        }


    }
}
