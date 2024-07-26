package com.vls.repository;

import com.vls.model.CourseModel;
import java.util.List;

public interface Course {
    List<CourseModel> getAllCourses();
    List<CourseModel> getCoursesByAuthor(String authorName);
    List<CourseModel> getCoursesByName(String courseName);
    void addCourse(CourseModel course);
    void deleteCourse(int courseId);

}
