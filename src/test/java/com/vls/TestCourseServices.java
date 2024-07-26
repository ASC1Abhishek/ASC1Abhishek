package com.vls;

import com.vls.model.CourseModel;
import com.vls.repository.Course;
import com.vls.repository.CourseImpl;
import com.vls.service.CourseService;
import com.vls.service.CourseServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCourseServices {

    private CourseService courseService;
    private Connection connection;

    @Before
    public void setUp() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vlsdb", "root", "mysql");

        Course course = new CourseImpl(connection);

        courseService = new CourseServiceImpl(course);

        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Course (CourseId INT PRIMARY KEY, CourseName VARCHAR(100), AuthorName VARCHAR(100), Duration INT, Availability BOOLEAN)");
            statement.execute("DELETE FROM Course");

            statement.execute("INSERT INTO Course (CourseId, CourseName, AuthorName, Duration, Availability) VALUES (1, 'java', 'subbu', 6, true)");
            statement.execute("INSERT INTO Course (CourseId, CourseName, AuthorName, Duration, Availability) VALUES (2, 'c', 'sanjay', 6, true)");
            statement.execute("INSERT INTO Course (CourseId, CourseName, AuthorName, Duration, Availability) VALUES (3, 'python', 'dakshan', 6, true)");
            statement.execute("INSERT INTO Course (CourseId, CourseName, AuthorName, Duration, Availability) VALUES (4, 'dsa', 'selva', 6, true)");
        }
    }

    @Test
    public void testGetAllCourses() {
        List<CourseModel> courses = courseService.getAllCourses();
        assertEquals(4, courses.size());
    }

    @Test
    public void testGetCoursesByAuthor() {
        List<CourseModel> courses = courseService.getCoursesByAuthor("subbu");
        assertEquals(1, courses.size());
        assertEquals("java", courses.get(0).getCourseName());
    }

    @Test
    public void testGetCoursesByName() {
        List<CourseModel> courses = courseService.getCoursesByName("java");
        assertEquals(1, courses.size());
        assertEquals("java", courses.get(0).getCourseName());
    }

    @Test
    public void testAddCourse() {
        CourseModel course = new CourseModel(5,"mysql", "nandhish", 6, true);
        courseService.addCourse(course);
        List<CourseModel> courses = courseService.getAllCourses();
        assertEquals(5, courses.size());
    }

    @Test
    public void testDeleteCourse() {
        courseService.deleteCourse(1);
        List<CourseModel> courses = courseService.getAllCourses();
        assertEquals(3, courses.size());
    }
}
