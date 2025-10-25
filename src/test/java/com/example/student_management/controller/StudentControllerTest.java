package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student testStudent;

    @BeforeEach
    void setUp() {
        // Initialize test data before each test
        testStudent = new Student();
        testStudent.setId(1);
        testStudent.setNom("LACHGAR");
        testStudent.setPrenom("Mohamed");
        testStudent.setDateNaissance(new Date());
    }

    @Test
    void testSaveStudent() {
        // Given
        Student student = new Student();
        student.setId(1);
        student.setNom("Mido");
        student.setPrenom("Test");

        // When
        when(studentService.save(any(Student.class))).thenReturn(student);
        ResponseEntity<Student> response = studentController.save(student);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Mido", response.getBody().getNom());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    void testDeleteStudent_Success() {
        // Given
        int studentId = 1;

        // When
        when(studentService.delete(studentId)).thenReturn(true);
        ResponseEntity<Void> response = studentController.delete(studentId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteStudent_NotFound() {
        // Given
        int studentId = 999;

        // When
        when(studentService.delete(studentId)).thenReturn(false);
        ResponseEntity<Void> response = studentController.delete(studentId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testFindAllStudents() {
        // Given
        Student student1 = new Student();
        student1.setId(1);
        student1.setNom("Student1");

        Student student2 = new Student();
        student2.setId(2);
        student2.setNom("Student2");

        List<Student> studentList = Arrays.asList(student1, student2);

        // When
        when(studentService.findAll()).thenReturn(studentList);
        ResponseEntity<List<Student>> response = studentController.findAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Student1", response.getBody().get(0).getNom());
        assertEquals("Student2", response.getBody().get(1).getNom());
    }

    @Test
    void testFindAllStudents_EmptyList() {
        // Given
        List<Student> emptyList = Arrays.asList();

        // When
        when(studentService.findAll()).thenReturn(emptyList);
        ResponseEntity<List<Student>> response = studentController.findAll();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testCountStudents() {
        // Given
        long expectedCount = 10L;

        // When
        when(studentService.countStudents()).thenReturn(expectedCount);
        ResponseEntity<Long> response = studentController.countStudent();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(10L, response.getBody());
    }

    @Test
    void testCountStudents_Zero() {
        // Given
        long expectedCount = 0L;

        // When
        when(studentService.countStudents()).thenReturn(expectedCount);
        ResponseEntity<Long> response = studentController.countStudent();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0L, response.getBody());
    }

    @Test
    void testFindByYear() {
        // Given
        List<Object[]> mockDataList = Arrays.asList(
            new Object[]{1985, 2L},
            new Object[]{1990, 3L}
        );

        // When
        doReturn(mockDataList).when(studentService).findNbrStudentByYear();
        ResponseEntity<Collection<?>> response = studentController.findByYear();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testFindByYear_EmptyResult() {
        // Given
        List<Object[]> emptyData = Arrays.asList();

        // When
        doReturn(emptyData).when(studentService).findNbrStudentByYear();
        ResponseEntity<Collection<?>> response = studentController.findByYear();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }
}
