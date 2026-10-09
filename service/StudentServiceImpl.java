package com.task.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.student.entity.Student;
import com.task.student.exception.DuplicateResourceException;
import com.task.student.exception.ResourceNotFoundException;
import com.task.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository sr;

    @Override
    @Transactional
    public Student registerStudent(Student student) {
        if (sr.existsByEmailAndCourseName(student.getEmail(),student.getCourseName())) {
            throw new DuplicateResourceException("Student with email " + student.getEmail()
                    + " is already registered for course " + student.getCourseName());
        }
        Student saved = sr.save(student);
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        List<Student> students = sr.findAll();
        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No student registrations found");
        }
        return students;
    }

    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        return sr.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        sr.delete(student);
    }
}

