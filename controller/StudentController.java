package com.task.student.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.student.entity.Student;
import com.task.student.service.StudentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@Validated
@RequiredArgsConstructor
public class StudentController {

	@Autowired
    StudentService ss;

    @PostMapping("/registration")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ss.registerStudent(student));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(ss.getAllStudents());
    }

    @GetMapping("/studentbyid/{id}")
    public ResponseEntity<Student> getStudentById(
            @PathVariable @Positive(message = "Student id must be a positive number") Long id) {
        return ResponseEntity.ok(ss.getStudentById(id));
    }

    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(
            @PathVariable @Positive(message = "Student id must be a positive number") Long id) {
        ss.deleteStudent(id);
        return ResponseEntity.ok(Map.of("message", "Registration deleted successfully for id: " + id));
    }
}
