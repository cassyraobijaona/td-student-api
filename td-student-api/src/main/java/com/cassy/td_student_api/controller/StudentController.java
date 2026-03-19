package com.cassy.td_student_api.controller;

import com.cassy.td_student_api.entity.Student;
import com.cassy.td_student_api.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public List<Student> addStudents(@RequestBody List<Student> students) {
        return studentService.addStudents(students);
    }
}