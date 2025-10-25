package com.example.student_management.service;

import com.example.student_management.entity.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    /**
     * Retrieve all students
     * @return List of all students
     */
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * Save or update a student
     * @param student the student to save
     * @return the saved student
     */
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Delete a student by ID
     * @param id the student's ID
     * @return true if deleted, false if not found
     */
    public boolean delete(int id) {
        Optional<Student> studentOptional = Optional.ofNullable(studentRepository.findById(id));
        if (studentOptional.isPresent()) {
            studentRepository.delete(studentOptional.get());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Count total number of students
     * @return the total count
     */
    public long countStudents() {
        return studentRepository.count();
    }

    /**
     * Get the number of students per birth year
     * @return Collection of Object arrays (year, count)
     */
    public Collection<?> findNbrStudentByYear() {
        return studentRepository.findNbrStudentByYear();
    }
}
