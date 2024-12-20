package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.AttendanceEntity;
import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    AttendanceEntity findByDateAndStudent(LocalDate date, StudentEntity student);
}
