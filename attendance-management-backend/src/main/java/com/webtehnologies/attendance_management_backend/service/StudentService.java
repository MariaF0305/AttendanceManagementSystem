package com.webtehnologies.attendance_management_backend.service;

import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import com.webtehnologies.attendance_management_backend.repository.GradeRepository;
import com.webtehnologies.attendance_management_backend.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

    public StudentEntity addStudent(StudentEntity student, Long gradeId) {
        if (gradeId == null) {
            throw new IllegalArgumentException("Grade ID is missing in the request.");
        }
        GradeEntity grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Grade ID"));
        student.setGrade(grade);

        return studentRepository.save(student);
    }

    public StudentEntity updateStudent(StudentEntity student) {
        Optional<StudentEntity> existingStudent = studentRepository.findById(student.getStudentId());
        if (existingStudent.isPresent()) {
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found");
        }
    }


    public StudentEntity findStudentById(Long id) {
        Optional<StudentEntity> student = studentRepository.findById(id);
        return student.orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<StudentEntity> findAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
