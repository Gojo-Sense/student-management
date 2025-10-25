package com.example.student_management.controller;

import com.example.student_management.entity.Student;
import com.example.student_management.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("students")
@Tag(name = "Student Management", description = "REST API for managing students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Save a new student or update an existing one
     * @param student the student data from request body
     * @return ResponseEntity with saved student and HTTP 201 Created
     */
    @Operation(
        summary = "Create or update a student",
        description = "Saves a new student to the database or updates an existing one based on the ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/save")
    public ResponseEntity<Student> save(
            @Parameter(description = "Student object to be saved", required = true)
            @RequestBody Student student) {
        Student savedStudent = studentService.save(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    /**
     * Delete a student by ID
     * @param id the student's ID from URL path
     * @return HTTP 204 No Content if deleted, 404 Not Found if not exists
     */
    @Operation(
        summary = "Delete a student",
        description = "Deletes a student from the database by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the student to be deleted", required = true)
            @PathVariable("id") int id) {
        boolean deleted = studentService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all students
     * @return ResponseEntity with list of all students and HTTP 200 OK
     */
    @Operation(
        summary = "Get all students",
        description = "Retrieves a complete list of all students in the database"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of students")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * Count total number of students
     * @return ResponseEntity with student count and HTTP 200 OK
     */
    @Operation(
        summary = "Count all students",
        description = "Returns the total number of students registered in the database"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved count")
    })
    @GetMapping("/count")
    public ResponseEntity<Long> countStudent() {
        long count = studentService.countStudents();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Get number of students grouped by birth year
     * @return ResponseEntity with statistics and HTTP 200 OK
     */
    @Operation(
        summary = "Get students by birth year",
        description = "Returns statistics showing the number of students grouped by their birth year"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved statistics")
    })
    @GetMapping("/byYear")
    public ResponseEntity<Collection<?>> findByYear() {
        Collection<?> studentsByYear = studentService.findNbrStudentByYear();
        return new ResponseEntity<>(studentsByYear, HttpStatus.OK);
    }
}
