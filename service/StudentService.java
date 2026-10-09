package com.task.student.service;

import java.util.List;

import com.task.student.entity.Student;

public interface StudentService {
	
    Student registerStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    void deleteStudent(Long id);
    
}