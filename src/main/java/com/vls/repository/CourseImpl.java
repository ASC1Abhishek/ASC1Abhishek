package com.vls.repository;

import com.vls.model.CourseModel;
import com.vls.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseImpl implements Course{

    @Override
    public List<CourseModel> getAllCourses() {
        List<CourseModel> courses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Course")) {

            while (resultSet.next()) {
                CourseModel course = new CourseModel();
                course.setCourseId(resultSet.getInt("CourseId"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setAuthorName(resultSet.getString("AuthorName"));
                course.setDuration(resultSet.getInt("Duration"));
                course.setAvailability(resultSet.getBoolean("Availability"));
                courses.add(course);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        }
        return courses;
    }

    @Override
    public List<CourseModel> getCoursesByAuthor(String authorName) {
        List<CourseModel> courses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course WHERE AuthorName = ?")) {
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CourseModel course = new CourseModel();
                course.setCourseId(resultSet.getInt("CourseId"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setAuthorName(resultSet.getString("AuthorName"));
                course.setDuration(resultSet.getInt("Duration"));
                course.setAvailability(resultSet.getBoolean("Availability"));
                courses.add(course);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        }
        return courses;
    }

    @Override
    public List<CourseModel> getCoursesByName(String courseName) {
        List<CourseModel> courses = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Course WHERE CourseName = ?")) {
            preparedStatement.setString(1, courseName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CourseModel course = new CourseModel();
                course.setCourseId(resultSet.getInt("CourseId"));
                course.setCourseName(resultSet.getString("CourseName"));
                course.setAuthorName(resultSet.getString("AuthorName"));
                course.setDuration(resultSet.getInt("Duration"));
                course.setAvailability(resultSet.getBoolean("Availability"));
                courses.add(course);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        }
        return courses;
    }

    @Override
    public void addCourse(CourseModel course) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Course (CourseName, AuthorName, Duration, Availability) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getAuthorName());
            preparedStatement.setInt(3, course.getDuration());
            preparedStatement.setBoolean(4, course.isAvailability());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Course WHERE CourseId = ?")) {
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("SQLException: " + sqlException.getMessage());
        }
    }




}
