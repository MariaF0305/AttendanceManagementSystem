package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.AttendanceEntity;
import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    AttendanceEntity findByDateAndStudent(LocalDate date, StudentEntity student);
    @Query("SELECT a FROM AttendanceEntity a WHERE a.student.grade.gradeId = :gradeId AND a.date = :date")
    List<AttendanceEntity> findByGradeAndDate(@Param("gradeId") Long gradeId, @Param("date") LocalDate date);
}

