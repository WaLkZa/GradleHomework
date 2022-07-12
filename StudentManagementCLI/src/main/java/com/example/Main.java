package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner input = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();

        boolean exit = false;

        do {
            System.out.println("1. Add a new course");
            System.out.println("2. Add a new student");
            System.out.println("3. Add a new teacher");
            System.out.println("4. Add a teacher to a specific course");
            System.out.println("5. Add a student to a specific course");
            System.out.println("6. Add a grade for student in a specific course");
            System.out.println("7. Show the average grade for all students in a specific course");
            System.out.println("8. Show a total average grade for student");
            System.out.println("9. Exit");

            System.out.print("Choice: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1:
                    addNewCourse(input, client, objectMapper);
                    break;
                case 2:
                    addNewStudent(input, client, objectMapper);
                    break;
                case 3:
                    addNewTeacher(input, client, objectMapper);
                    break;
                case 4:
                    addTeacherToSpecificCourse(input, client, objectMapper);
                    break;
                case 5:
                    addStudentToSpecificCourse(input, client, objectMapper);
                    break;
                case 6:
                    addGradeForStudentInASpecificCourse(input, client, objectMapper);
                    break;
                case 7:
                    getAverageGradeForAllSstudentsInSpecificCourse(input, client);
                    break;
                case 8:
                    getAverageGradeForStudent(input, client);
                    break;
                case 9:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private static void addNewCourse(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Name:");
        String courseName = input.nextLine();
        System.out.println("Total hours:");
        String courseTotalHours = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("name", courseName);
            put("totalHours", courseTotalHours);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/courses"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void addNewStudent(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Name:");
        String studentName = input.nextLine();
        System.out.println("Age:");
        String studentAge = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("name", studentName);
            put("age", studentAge);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/students"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void addNewTeacher(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Name:");
        String teacherName = input.nextLine();
        System.out.println("Degree:");
        String teacherDegree = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("name", teacherName);
            put("degree", teacherDegree);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/teachers"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void addTeacherToSpecificCourse(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Course ID:");
        String courseId = input.nextLine();
        System.out.println("Teacher ID:");
        String teacherId = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("courseId", courseId);
            put("teacherId", teacherId);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/courses/add-teacher"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void addStudentToSpecificCourse(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Course ID:");
        String courseId = input.nextLine();
        System.out.println("Student ID:");
        String studentId = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("courseId", courseId);
            put("studentId", studentId);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/students/add-course"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void addGradeForStudentInASpecificCourse(Scanner input, HttpClient client, ObjectMapper objectMapper) throws IOException, InterruptedException {
        System.out.println("Course ID:");
        String courseId = input.nextLine();
        System.out.println("Student ID:");
        String studentId = input.nextLine();
        System.out.println("Grade:");
        String grade = input.nextLine();

        Map<String, String> values = new HashMap<>() {{
            put("courseId", courseId);
            put("studentId", studentId);
            put("grade", grade);
        }};

        String requestBody = objectMapper.writeValueAsString(values);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create("http://localhost:8080/grades/add-grade"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void getAverageGradeForAllSstudentsInSpecificCourse(Scanner input, HttpClient client) throws IOException, InterruptedException {
        System.out.println("Course ID:");
        String courseId = input.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("http://localhost:8080/grades/average-in-course/%s", courseId)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }

    private static void getAverageGradeForStudent(Scanner input, HttpClient client) throws IOException, InterruptedException {
        System.out.println("Student ID:");
        String studentId = input.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("http://localhost:8080/grades/average-for-student/%s", studentId)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status code: " + response.statusCode());
        System.out.println("Body: " + response.body());
    }
}