package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    List<StudentEntity> findStudentsByGrade_GradeId(Long gradeId);
}
