package com.vls.controller;

import com.vls.model.CourseModel;
import com.vls.service.CourseService;
import com.vls.service.CourseServiceImpl;

import java.util.List;

public class CourseController {
    private final CourseService courseService = new CourseServiceImpl();

    public List<CourseModel> getAllCourses() {
        return courseService.getAllCourses();
    }

    public List<CourseModel> getCoursesByAuthor(String authorName) {
        return courseService.getCoursesByAuthor(authorName);
    }

    public List<CourseModel> getCoursesByName(String courseName) {
        return courseService.getCoursesByName(courseName);
    }

    public void addCourse(CourseModel course) {
        courseService.addCourse(course);
    }

    public void deleteCourse(int courseId) {
        courseService.deleteCourse(courseId);
    }

}
