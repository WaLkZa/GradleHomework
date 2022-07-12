package com.example.studentmanagementdomain.service.impl;

import com.example.studentmanagementdomain.exception.CourseNotFoundException;
import com.example.studentmanagementdomain.exception.StudentNotFoundException;
import com.example.studentmanagementdomain.model.dto.GradeStudentCourseDto;
import com.example.studentmanagementdomain.model.entity.Course;
import com.example.studentmanagementdomain.model.entity.Grade;
import com.example.studentmanagementdomain.model.entity.Student;
import com.example.studentmanagementdomain.repository.CourseRepository;
import com.example.studentmanagementdomain.repository.GradeRepository;
import com.example.studentmanagementdomain.repository.StudentRepository;
import com.example.studentmanagementdomain.service.GradeService;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Double getAverageInCourse(Long courseId) {
        this.courseRepository.findById(courseId).orElseThrow(CourseNotFoundException::new);

        return this.gradeRepository.findAverageGradeInCourse(courseId);
    }

    @Override
    public Double getAverageForStudent(Long studentId) {
        this.studentRepository.findById(studentId).orElseThrow(StudentNotFoundException::new);

        return this.gradeRepository.findAverageGradeForStudent(studentId);
    }

    @Override
    public Grade addGradeForStudentInCourse(GradeStudentCourseDto newGrade) {
        Course findCourse = this.courseRepository.findById(newGrade.getCourseId()).orElseThrow(CourseNotFoundException::new);
        Student findStudent = this.studentRepository.findById(newGrade.getStudentId()).orElseThrow(StudentNotFoundException::new);

        return this.gradeRepository.save(
                new Grade(findStudent, findCourse, newGrade.getGrade()));
    }
}
