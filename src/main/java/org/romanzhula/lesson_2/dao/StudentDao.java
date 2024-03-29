package org.romanzhula.lesson_2.dao;

import org.romanzhula.lesson_1.util.ConnectionManager;
import org.romanzhula.lesson_2.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDao {

    //create our singleton for other calls in different places
    private static final StudentDao INSTANCE = new StudentDao();

    private static final String SAVE_SQL = """
                INSERT INTO students (first_name, last_name, email) VALUES
                (?, ?, ?);
            """;

    private static final String FIND_BY_ID_SQL = """
                SELECT * FROM students WHERE id = ?;
            """;

    //constructor for blocking create new objects of this class
    private StudentDao() {}

    //caller for singleton
    public static StudentDao getInstance() {
        return INSTANCE;
    }

    public Student save(Student student) {
        try (
              var connection = ConnectionManager.open();
              var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
                ) {

            prepareStatement.setString(1, student.getFirstName());
            prepareStatement.setString(2, student.getLastName());
            prepareStatement.setString(3, student.getEmail());

            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt("id"));
            }

            return student;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Student findById(Integer id) {
        try (
                var connection = ConnectionManager.open();
                var prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)
        ) {

            prepareStatement.setInt(1, id);

            ResultSet resultSetStudentById = prepareStatement.executeQuery();

            Student student = new Student();

            if (resultSetStudentById.next()){
                student.setId(resultSetStudentById.getInt("id"));
                student.setFirstName(resultSetStudentById.getString("first_name"));
                student.setLastName(resultSetStudentById.getString("last_name"));
                student.setEmail(resultSetStudentById.getString("email"));
            }

            return student;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
