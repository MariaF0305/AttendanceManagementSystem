package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
}
