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

        String insertStudentsSql = """
                    INSERT INTO students (id, first_name, last_name, email) VALUES
                    (1, 'Rom', 'Man', 'roman@net.test')
                """;

        String dropStudentsSql = """
                    DROP TABLE students;
                """;

        try (
            var connection = ConnectionManager.open();
            var statement = connection.createStatement()
        ) {
//            statement.execute(createStudentTableSql); //add table
//            statement.executeUpdate(insertStudentsSql); //add row to table (one student)
            statement.execute(dropStudentsSql); //remove table

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}