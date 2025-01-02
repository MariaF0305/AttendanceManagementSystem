package com.webtehnologies.attendance_management_backend.dto;

public class StudentDTO {
    private Long studentId;
    private String studentName;
    private boolean attendanceMarkedForDate;

    public StudentDTO() {
    }

    public StudentDTO(Long studentId, String studentName, boolean attendanceMarkedForDate) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.attendanceMarkedForDate = attendanceMarkedForDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isAttendanceMarkedForDate() {
        return attendanceMarkedForDate;
    }

    public void setAttendanceMarkedForDate(boolean attendanceMarkedForDate) {
        this.attendanceMarkedForDate = attendanceMarkedForDate;
    }
}
