package com.cassy.td_student_api.controller;

import com.cassy.td_student_api.entity.Student;
import com.cassy.td_student_api.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudents(@RequestBody List<Student> students) {
        try {
            List<Student> allStudents = studentService.addStudents(students);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(allStudents);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader(value = "Accept", required = false) String accept) {

        if (accept == null || !accept.equals("text/plain")) {
            return "Format non supporté";
        }

        List<Student> students = studentService.getStudents();

        String result = "";

        for (Student s : students) {
            result += s.getFirstName() + " " + s.getLastName() + "\n";
        }

        return result;
    }
}