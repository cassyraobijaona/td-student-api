package com.cassy.td_student_api.validator;

import com.cassy.td_student_api.entity.Student;
import com.cassy.td_student_api.exception.BadRequestException;

import java.util.List;

public class StudentValidator {

    public static void validate(List<Student> students) {

        for (Student s : students) {

            if (s.getReference() == null || s.getReference().isBlank()) {
                throw new BadRequestException("Reference is required");
            }

            if (s.getFirstName() == null || s.getFirstName().isBlank()) {
                throw new BadRequestException("FirstName is required");
            }

            if (s.getLastName() == null || s.getLastName().isBlank()) {
                throw new BadRequestException("LastName is required");
            }
        }
    }
}
