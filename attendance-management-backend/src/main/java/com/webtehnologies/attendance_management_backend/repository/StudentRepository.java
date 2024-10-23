package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
