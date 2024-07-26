package com.vls.ui;

import com.vls.exception.CourseNotFoundException;
import com.vls.exception.DatabaseException;
import com.vls.model.CourseModel;
import com.vls.repository.Course;
import com.vls.repository.CourseImpl;
import com.vls.repository.Login;
import com.vls.repository.LoginImpl;
import com.vls.service.CourseService;
import com.vls.service.CourseServiceImpl;
import com.vls.service.LoginService;

import java.util.List;
import java.util.Scanner;

public class MainUI {

    private final CourseService courseService;
    private final LoginService loginService;
    private final Scanner scanner;
    private boolean loggedIn = false;

    public MainUI(CourseService courseService, LoginService loginService) {
        this.courseService = courseService;
        this.loginService = loginService;
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Virtual Learning System");
        while (true) {
            if (!loggedIn) {
                printLoginMenu();
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("Exiting..........");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                printMainMenu();
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        searchByAuthor();
                        break;
                    case 2:
                        searchByName();
                        break;
                    case 3:
                        displayAllCourses();
                        break;
                    case 4:
                        addCourseToCart();
                        break;
                    case 5:
                        deleteCourseFromCart();
                        break;
                    case 6:
                        System.out.println("Logging Out.......");
                        loggedIn = false;
                        break;
                    case 7:
                        System.out.println("Exiting.......");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }

    private void printLoginMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    private void printMainMenu() {
        System.out.println("1. Search Course by Author");
        System.out.println("2. Search Course by Name");
        System.out.println("3. Display All Courses");
        System.out.println("4. Add Course");
        System.out.println("5. Delete Course");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
    }

    private void login() {
        System.out.print("Enter UserName: ");
        String userName = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        try {
            if (loginService.authenticate(userName, password)) {
                loggedIn = true;
                System.out.println("Login successful.");
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void register() {
        System.out.print("Enter New UserName: ");
        String userName = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();
        try {
            loginService.registerUser(userName, password);
            System.out.println("Registration successful. Please login.");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchByAuthor() {
        System.out.println("Enter Author Name");
        String authorName = scanner.next();
        List<CourseModel> courses = courseService.getCoursesByAuthor(authorName);
        displayCourses(courses);
    }

    private void searchByName() {
        System.out.println("Enter Name");
        String name = scanner.next();
        List<CourseModel> courses = courseService.getCoursesByName(name);
        displayCourses(courses);
    }

    private void displayAllCourses() {
        List<CourseModel> courses = courseService.getAllCourses();
        displayCourses(courses);
    }

    private void addCourseToCart() {
        System.out.println("Enter Course ID");
        int courseId = scanner.nextInt();
        System.out.println("Enter Course Name");
        String courseName = scanner.next();
        System.out.println("Enter Author Name");
        String authorName = scanner.next();
        System.out.println("Enter Course Duration");
        int duration = scanner.nextInt();
        System.out.println("Enter Course Availability");
        boolean availability = scanner.nextBoolean();

        try {
            CourseModel courseModel = new CourseModel(courseId, courseName, authorName, duration, availability);
            courseService.addCourse(courseModel);
            System.out.println("Course added successfully.");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteCourseFromCart() {
        System.out.println("Enter Course ID");
        int courseId = scanner.nextInt();

        try {
            courseService.deleteCourse(courseId);
            System.out.println("Course deleted successfully.");
        } catch (CourseNotFoundException e) {
            System.out.println("Error: Course not found.");
        } catch (DatabaseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void displayCourses(List<CourseModel> courses) {
        if (courses.isEmpty()) {
            System.out.println("No courses found");
        } else {
            for (CourseModel course : courses) {
                System.out.println("Course ID: " + course.getCourseId());
                System.out.println("Course Name: " + course.getCourseName());
                System.out.println("Author Name: " + course.getAuthorName());
                System.out.println("Duration: " + course.getDuration());
                System.out.println("Availability: " + course.isAvailability());
                System.out.println("------------------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Course courseRepo = new CourseImpl();
        CourseService courseService = new CourseServiceImpl(courseRepo);
        Login loginRepo = new LoginImpl();
        LoginService loginService = new LoginService(loginRepo);

        MainUI ui = new MainUI(courseService, loginService);
        ui.start();
    }
}
