package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.view.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student getStudentById(Integer id) throws Exception {
        Student student = new Student();
        try {
            student = studentRepository.queryById(id);
        } catch (Exception ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student;
    }

    @Transactional
    public Student postStudent(Student student) throws Exception {
        Student student1 = new Student();
        try {
//            student.setId(studentView.getId());
            student1.setName(student.getName());
            student1.setMajor(student.getMajor());
            student1.setGpa(student.getGpa());
            studentRepository.save(student);
        } catch (Exception ex) {
            Logger.getLogger(StudentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return student1;
    }
}
