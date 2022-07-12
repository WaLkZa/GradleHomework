package com.example.studentmanagementdomain.service;

import com.example.studentmanagementdomain.model.dto.CourseStudentDto;
import com.example.studentmanagementdomain.model.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<Student> getAll();

    Optional<Student> getById(Long id);

    Student create(Student newStudent);

    void delete(Long id);

    Student addStudentToCourse(CourseStudentDto courseStudentDto);
}
