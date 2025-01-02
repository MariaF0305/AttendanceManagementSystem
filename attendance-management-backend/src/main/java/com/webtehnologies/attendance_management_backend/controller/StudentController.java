package com.webtehnologies.attendance_management_backend.controller;

import com.webtehnologies.attendance_management_backend.dto.StudentDTO;
import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import com.webtehnologies.attendance_management_backend.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntity>> getAllStudents () {
        List<StudentEntity> students = studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<StudentEntity> getStudentById (@PathVariable("id") Long id) {
        StudentEntity student = studentService.findStudentById(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/add/{gradeId}")
    public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentEntity student, @PathVariable Long gradeId) {
        StudentEntity newStudent = studentService.addStudent(student, gradeId);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<StudentEntity> updatedStudent(@RequestBody StudentEntity student) {
        StudentEntity updateStudent = studentService.updateStudent(student);
        GradeEntity updateGrade = updateStudent.getGrade();
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("grades/{gradeId}")
    public ResponseEntity<List<StudentEntity>> getStudentsByGradeId(@PathVariable Long gradeId) {
        List<StudentEntity> students = studentService.findStudentsByGradeId(gradeId);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/grades-flag/{gradeId}")
    public ResponseEntity<List<StudentDTO>> getStudentsWithAttendanceFlag(
            @PathVariable Long gradeId,
            @RequestParam("date") LocalDate date) {
        List<StudentDTO> students = studentService.getStudentsWithAttendanceFlag(gradeId, date);
        return ResponseEntity.ok(students);
    }

}
