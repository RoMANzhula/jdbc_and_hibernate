package org.romanzhula.lesson_2;

import org.romanzhula.lesson_1.util.ConnectionManager;
import org.romanzhula.lesson_2.dao.StudentDao;
import org.romanzhula.lesson_2.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;


public class JdbcAdvanced {
    public static void main(String[] args) {
        StudentDao studentDao = StudentDao.getInstance();

//        Student student = new Student();
//        student.setFirstName("Pro");
//        student.setLastName("Sto");
//        student.setEmail("prosto@net.test");
//
//        Student studentToDb = studentDao.save(student);
//        System.out.println(studentToDb);

        //get student from DB by id
        var studentById = studentDao.findById(1);
//        var studentById = studentDao.findById(11111); //if we searching student with unreal id - we get all fields equals NULL
        System.out.println(studentById.toString());

    }
}


//public class JdbcAdvanced {
//
//    public static void main(String[] args) {
////        String studentIdAsString = "2 OR 1 = 1"; //SQL-injection ("2 OR 1 = 1" == "2 OR true") ("2; DROP TABLE test" - this injection removing table "test")
//        Integer studentId = 2;
//        String email = "maksym@net.test OR true"; //this sql-request will be false
////        String email = "maksym@net.test"; //this sql-request will be true
//
//        getStudents(studentId, email);
//    }
//
//    private static void getStudents(Integer id, String email) {
////        String sql = """
////                    SELECT * FROM students WHERE id = %s;  //this method not safe
////                """.formatted(id);
//        String sql = """
//                    SELECT * FROM students WHERE id = ? OR email = ?;
//                """;
//
//        try (
//                var connection = ConnectionManager.open();
////                var statement = connection.createStatement() //this method not safe we need use prepareStatement
//                var prepareStatement = connection.prepareStatement(sql);
//        ){
//            prepareStatement.setInt(1, id); //count indexes starts from 1 (not 0)
//            prepareStatement.setString(2, email);
////            var resultSet = statement.executeQuery(sql); //this method not safe we need use prepareStatement
//            var resultSet = prepareStatement.executeQuery(); //method must be without parameters
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
//                System.out.println(resultSet.getString("first_name"));
//                System.out.println(resultSet.getString("last_name"));
//                System.out.println(resultSet.getString("email"));
//                System.out.println("------------------");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//}

//                           CONCLUSION
// PrepareStatement help us ignored INJECTIONS and our data will be under protection