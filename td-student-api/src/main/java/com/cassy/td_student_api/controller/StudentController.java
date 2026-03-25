package com.cassy.td_student_api.controller;

import com.cassy.td_student_api.entity.Student;
import com.cassy.td_student_api.exception.BadRequestException;
import com.cassy.td_student_api.service.StudentService;
import com.cassy.td_student_api.validator.StudentValidator;
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
    public ResponseEntity<?> addStudents(@RequestBody List<Student> students) {
        try {
            StudentValidator.validate(students);

            List<Student> allStudents = studentService.addStudents(students);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(allStudents);

        } catch (BadRequestException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(@RequestHeader(value = "Accept", required = false) String accept) {
        try {
            if (accept == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Entête 'Accept' manquant");
            }

            if (!accept.equals("application/json") && !accept.equals("text/plain")) {
                return ResponseEntity
                        .status(HttpStatus.NOT_IMPLEMENTED)
                        .body("Format non supporté");
            }

            List<Student> students = studentService.getStudents();

            if (accept.equals("text/plain")) {
                StringBuilder sb = new StringBuilder();
                for (Student s : students) {
                    sb.append(s.getFirstName()).append(" ").append(s.getLastName()).append("\n");
                }
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(sb.toString());
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(students);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur serveur");
        }
    }
}