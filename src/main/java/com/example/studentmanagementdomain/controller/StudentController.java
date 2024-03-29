package com.example.studentmanagementdomain.controller;

import com.example.studentmanagementdomain.model.entity.Student;
import com.example.studentmanagementdomain.model.dto.CourseStudentDto;
import com.example.studentmanagementdomain.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(this.studentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") long id) {
        Optional<Student> studentData = this.studentService.getById(id);

        return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student newStudent) {
        return new ResponseEntity<>(this.studentService.create(newStudent), HttpStatus.CREATED);
    }

    @PostMapping("/add-course")
    public ResponseEntity<Student> addStudentToCourse(@RequestBody CourseStudentDto courseStudentDto) {
        return new ResponseEntity<>(this.studentService.addStudentToCourse(courseStudentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        this.studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
