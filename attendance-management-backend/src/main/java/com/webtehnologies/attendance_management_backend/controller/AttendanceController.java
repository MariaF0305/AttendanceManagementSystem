package com.webtehnologies.attendance_management_backend.controller;

import com.webtehnologies.attendance_management_backend.model.AttendanceEntity;
import com.webtehnologies.attendance_management_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/save")
    public ResponseEntity<Void> saveAttendance(@RequestBody List<AttendanceEntity> attendanceList) {
        try {
            attendanceService.saveAttendance(attendanceList);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Boolean> checkAttendanceStatus(
            @RequestParam Long gradeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        boolean isAttendanceSaved = attendanceService.isAttendanceSavedForDate(gradeId, date);
        return ResponseEntity.ok(isAttendanceSaved);
    }
}
