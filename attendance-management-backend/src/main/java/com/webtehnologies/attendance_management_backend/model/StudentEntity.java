package com.webtehnologies.attendance_management_backend.model;

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
    private List<AttendanceEntity> attendanceRecords;

    public StudentEntity() {
    }

    public StudentEntity(Long studentId, GradeEntity grade, String studentName) {
        this.studentId = studentId;
        this.grade = grade;
        this.studentName = studentName;
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
}
