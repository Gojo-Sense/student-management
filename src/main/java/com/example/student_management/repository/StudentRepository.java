package com.example.student_management.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.student_management.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    /**
     * Find a student by their ID
     * @param id the student's ID
     * @return the Student entity
     */
    Student findById(int id);

    /**
     * Custom query to count the number of students per birth year
     * @return Collection of Object arrays where:
     *         - First element is the birth year
     *         - Second element is the count of students
     */
    @Query("SELECT YEAR(s.dateNaissance), COUNT(s) FROM Student s GROUP BY YEAR(s.dateNaissance)")
    Collection<Object[]> findNbrStudentByYear();
}
