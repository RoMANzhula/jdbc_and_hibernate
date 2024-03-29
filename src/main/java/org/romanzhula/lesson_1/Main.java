package org.romanzhula.lesson_1;

import org.romanzhula.lesson_1.util.ConnectionManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String createStudentTableSql = """
                    CREATE TABLE IF NOT EXISTS students (
                        id INT PRIMARY KEY,
                        first_name VARCHAR(50),
                        last_name VARCHAR(50),
                        email VARCHAR(100)
                    );
                """;

        String createCoursesTableSql = """
                    CREATE TABLE IF NOT EXISTS courses (
                        id INT PRIMARY KEY,
                        course_name VARCHAR(100),
                        description VARCHAR(100),
                        duration INT
                    );
                """;

        String createEnrollmentTableSql = """
                    CREATE TABLE IF NOT EXISTS enrollment (
                        id INT PRIMARY KEY,
                        student_id INT,
                        course_id INT,
                        FOREIGN KEY (student_id) REFERENCES students(id),
                        FOREIGN KEY (course_id) REFERENCES courses(id)
                    );
                """;

        String insertStudentsSql = """
                    INSERT INTO students (id, first_name, last_name, email) VALUES
                    (1, 'Rom', 'Man', 'roman@net.test'),
                    (2, 'Mak', 'Sym', 'maksym@net.test'),
                    (3, 'And', 'Rey', 'andrey@net.test');
                """;

        String insertCoursesSql = """
                    INSERT INTO courses (id, course_name, description, duration) VALUES
                    (1, 'Java Start', 'Basic information about Java', 30),
                    (2, 'JDBC', 'Basic information about JDBC', 25);
                """;

        String insertEnrollmentSql = """
                    INSERT INTO enrollment (id, student_id, course_id) VALUES
                    (1, 1, 1),
                    (2, 2, 1),
                    (3, 2, 2),
                    (4, 3, 2);
                """;

        String dropStudentsSql = """
                    DROP TABLE students;
                """;

        try (
            var connection = ConnectionManager.open();
            var statement = connection.createStatement()
        ) {
//            statement.execute(createStudentTableSql); //add table
//            statement.execute(createCoursesTableSql); //add table
//            statement.execute(createEnrollmentTableSql); //add table
            statement.execute(insertCoursesSql); //add row to table
            statement.execute(insertEnrollmentSql); //add row to table
//            statement.executeUpdate(insertStudentsSql); //add row to table (one student)
//            statement.execute(dropStudentsSql); //remove table

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}