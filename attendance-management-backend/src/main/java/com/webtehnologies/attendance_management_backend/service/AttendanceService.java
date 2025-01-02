package com.webtehnologies.attendance_management_backend.service;

import com.webtehnologies.attendance_management_backend.model.AttendanceEntity;
import com.webtehnologies.attendance_management_backend.model.StudentEntity;
import com.webtehnologies.attendance_management_backend.repository.AttendanceRepository;
import com.webtehnologies.attendance_management_backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private StudentRepository studentRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public void saveAttendance(List<AttendanceEntity> attendanceList) {
        if (attendanceList == null || attendanceList.isEmpty()) {
            throw new IllegalStateException("Attendance list cannot be empty");
        }

        for (AttendanceEntity attendance : attendanceList) {
            StudentEntity student = studentRepository.findById(attendance.getStudent().getStudentId())
                    .orElseThrow(() -> new IllegalStateException("Student not found"));

            AttendanceEntity existingRecord = attendanceRepository.findByDateAndStudent(attendance.getDate(), student);


            if (existingRecord != null && existingRecord.getLocked()) {
                throw new IllegalStateException("Attendance for this student on the selected date is locked.");
            }

            if (existingRecord != null) {
                existingRecord.setStatus(attendance.getStatus());
                existingRecord.setLocked(true);
                attendanceRepository.save(existingRecord);
            } else {
                attendance.setLocked(true);
                attendanceRepository.save(attendance);
                }

            if (attendance.getStatus() != null && attendance.getStatus()) {
                student.incrementNrAttendance();
                studentRepository.save(student);
            }
        }
    }

    public boolean isAttendanceSavedForDate(Long gradeId, LocalDate date) {
        List<AttendanceEntity> attendanceRecords = attendanceRepository.findByGradeAndDate(gradeId, date);
        return attendanceRecords != null && !attendanceRecords.isEmpty();
    }

}
