package com.cassy.td_student_api.service;

import com.cassy.td_student_api.entity.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students = new ArrayList<>();

    public List<Student> addStudents(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

    public List<Student> getStudents() {
        return students;
    }
}