package com.webtehnologies.attendance_management_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "student")
public class StudentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long studentId;
    private String studentName;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    private GradeEntity grade;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AttendanceEntity> attendanceRecords;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer nrAttendance = 0;

    @Transient
    private boolean attendanceMarkedForDate;

    public StudentEntity() {
    }

    public StudentEntity(Long studentId, GradeEntity grade, String studentName) {
        this.studentId = studentId;
        this.grade = grade;
        this.studentName = studentName;
    }

    public void incrementNrAttendance() {
        if (this.nrAttendance == null) {
            this.nrAttendance = 0;
        }
        this.nrAttendance++;
    }


    public boolean isAttendanceMarkedForDate() {
        return attendanceMarkedForDate;
    }

    public void setAttendanceMarkedForDate(boolean attendanceMarkedForDate) {
        this.attendanceMarkedForDate = attendanceMarkedForDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public GradeEntity getGrade() {
        return grade;
    }

    public void setGrade(GradeEntity gradeEntity) {
        this.grade = gradeEntity;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<AttendanceEntity> getAttendanceRecords() {
        return attendanceRecords;
    }

    public void setAttendanceRecords(List<AttendanceEntity> attendanceRecords) {
        this.attendanceRecords = attendanceRecords;
    }

    public int getNrAttendance() {
        return nrAttendance;
    }

    public void setNrAttendance(int nrAttendance) {
        this.nrAttendance = nrAttendance;
    }
}
