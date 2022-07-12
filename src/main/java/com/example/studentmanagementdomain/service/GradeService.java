package com.example.studentmanagementdomain.service;


import com.example.studentmanagementdomain.model.dto.GradeStudentCourseDto;
import com.example.studentmanagementdomain.model.entity.Grade;

public interface GradeService {

    Double getAverageInCourse(Long courseId);

    Double getAverageForStudent(Long studentId);

    Grade addGradeForStudentInCourse(GradeStudentCourseDto newGrade);
}
