package org.romanzhula.lesson_2;

import org.romanzhula.lesson_1.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcTransaction {

    public static void main(String[] args) throws SQLException {
        Integer courseIdForDelete = 2;

        String deleteEnrollmentSql = "DELETE FROM enrollment WHERE course_id = ?";
        String deleteCoursesSql = "DELETE FROM courses WHERE id = ?";

        Connection connection = null;
        PreparedStatement deleteEnrollmentPrepareStatement = null;
        PreparedStatement deleteCoursesPrepareStatement = null;

        try {
            connection = ConnectionManager.open();
            deleteEnrollmentPrepareStatement = connection.prepareStatement(deleteEnrollmentSql);
            deleteCoursesPrepareStatement = connection.prepareStatement(deleteCoursesSql);

            connection.setAutoCommit(false); //for manual enable commit to our prepareStatements

            deleteEnrollmentPrepareStatement.setInt(1, courseIdForDelete);
            deleteCoursesPrepareStatement.setInt(1, courseIdForDelete);

            deleteEnrollmentPrepareStatement.executeUpdate();

            if (true) { //if we remove this block - that we delete data without problem with Exceptions and Rollback
                throw new RuntimeException("Something happened...");
            }

            deleteCoursesPrepareStatement.executeUpdate();

            connection.commit(); //manual commit

        } catch (Exception e) {

            if (connection != null) {
                connection.rollback();
            }

            throw e;

        } finally {

            if (connection != null) {
                connection.close();
            }

            if (deleteCoursesPrepareStatement != null) {
                deleteCoursesPrepareStatement.close();
            }

            if (deleteEnrollmentPrepareStatement != null) {
                deleteEnrollmentPrepareStatement.close();
            }
        }

    }

}

//              CONCLUSION
//manual commit give us possible to managing situations when our transactions (int prepareStatements) happened
//even our block catch throws Exception
