package com.example.studentmanagementdomain.service;

import com.example.studentmanagementdomain.model.dto.CourseTeacherDto;
import com.example.studentmanagementdomain.model.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getAll();

    Optional<Course> getById(Long id);

    Course create(Course newCourse);

    void delete(Long id);

    Course addTeacherToCourse(CourseTeacherDto courseTeacherDto);
}
