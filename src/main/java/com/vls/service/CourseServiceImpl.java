package com.vls.service;

import com.vls.model.CourseModel;
import com.vls.repository.Course;
import com.vls.repository.CourseImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private Course courseRepo = new CourseImpl();

    public CourseServiceImpl() {}
    public CourseServiceImpl(Course courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Override
    public List<CourseModel> getAllCourses() {
        return courseRepo.getAllCourses();
    }

    @Override
    public List<CourseModel> getCoursesByAuthor(String authorName) {
        return courseRepo.getCoursesByAuthor(authorName);
    }

    @Override
    public List<CourseModel> getCoursesByName(String courseName) {
        return courseRepo.getCoursesByName(courseName);
    }

    @Override
    public void addCourse(CourseModel course) {
        courseRepo.addCourse(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseRepo.deleteCourse(courseId);
    }

}
