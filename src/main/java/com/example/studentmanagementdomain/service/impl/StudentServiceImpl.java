package com.example.studentmanagementdomain.service.impl;

import com.example.studentmanagementdomain.exception.CourseNotFoundException;
import com.example.studentmanagementdomain.exception.StudentNotFoundException;
import com.example.studentmanagementdomain.model.dto.CourseStudentDto;
import com.example.studentmanagementdomain.model.entity.Course;
import com.example.studentmanagementdomain.model.entity.Student;
import com.example.studentmanagementdomain.repository.CourseRepository;
import com.example.studentmanagementdomain.repository.StudentRepository;
import com.example.studentmanagementdomain.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return this.studentRepository.findById(id);
    }

    @Override
    public Student create(Student newStudent) {
        return this.studentRepository.save(new Student(newStudent.getName(), newStudent.getAge()));
    }

    @Override
    public void delete(Long id) {
        this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        this.studentRepository.deleteById(id);
    }

    @Override
    public Student addStudentToCourse(CourseStudentDto courseStudentDto) {
        Course findCourse = this.courseRepository.findById(courseStudentDto.getCourseId()).orElseThrow(CourseNotFoundException::new);
        Student findStudent = this.studentRepository.findById(courseStudentDto.getStudentId()).orElseThrow(StudentNotFoundException::new);

        findStudent.getCourses().add(findCourse);
        findCourse.getStudents().add(findStudent);

        this.studentRepository.save(findStudent);
        this.courseRepository.save(findCourse);

        return findStudent;
    }
}
