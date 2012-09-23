package com.thoughtworks.universityregistration.framework.datasetup;

import com.thoughtworks.universityregistration.framework.Framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataWriter {

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/UniversityRegistration", "unireg", "unireg");
        } catch (SQLException e) {
            System.out.println(e.toString());
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public static void persistData() throws SQLException {
        persistCourses();
    }

    private static void persistCourses() throws SQLException {
        List<CourseData> courses = Framework.getAll(CourseData.class);
        for (CourseData course : courses) {
            String sql = "INSERT Course VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, course.getName());
            statement.executeUpdate();
        }
    }

    public static void clearData() throws SQLException {
        String sql = "DELETE FROM Course";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
    }


}
