package com.example.studentmanagementdomain.controller;

import com.example.studentmanagementdomain.model.entity.Student;
import com.example.studentmanagementdomain.model.entity.Teacher;
import com.example.studentmanagementdomain.repository.TeacherRepository;
import com.example.studentmanagementdomain.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;


    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return new ResponseEntity<>(this.teacherService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable("id") long id) {
        Optional<Teacher> teacherData = this.teacherService.getById(id);

        return new ResponseEntity<>(teacherData.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher newTeacher) {
        return new ResponseEntity<>(this.teacherService.create(newTeacher), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        this.teacherService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
