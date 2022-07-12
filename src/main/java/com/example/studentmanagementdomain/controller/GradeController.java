package com.example.studentmanagementdomain.controller;

import com.example.studentmanagementdomain.model.entity.Grade;
import com.example.studentmanagementdomain.model.dto.GradeStudentCourseDto;
import com.example.studentmanagementdomain.service.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/average-in-course/{courseId}")
    public ResponseEntity<Double> getAverageInCourse(@PathVariable("courseId") Long courseId) {
        return new ResponseEntity<>(this.gradeService.getAverageInCourse(courseId), HttpStatus.OK);
    }

    @GetMapping("/average-for-student/{studentId}")
    public ResponseEntity<Double> getAverageForStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(this.gradeService.getAverageForStudent(studentId), HttpStatus.OK);
    }

    @PostMapping("/add-grade")
    public ResponseEntity<Grade> addGradeForStudentInCourse(@RequestBody GradeStudentCourseDto newGrade) {
        return new ResponseEntity<>(this.gradeService.addGradeForStudentInCourse(newGrade), HttpStatus.CREATED);
    }
}
