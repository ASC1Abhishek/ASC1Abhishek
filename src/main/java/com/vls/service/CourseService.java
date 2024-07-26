package com.vls.service;

import com.vls.model.CourseModel;

import java.util.List;

public interface CourseService {
    List<CourseModel> getAllCourses();
    List<CourseModel> getCoursesByAuthor(String authorName);
    List<CourseModel> getCoursesByName(String courseName);
    void addCourse(CourseModel course);
    void deleteCourse(int courseId);
}
