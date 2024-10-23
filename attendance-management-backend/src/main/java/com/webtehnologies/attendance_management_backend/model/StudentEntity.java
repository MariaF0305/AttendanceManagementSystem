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
    private Long studenId;
    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private GradeEntity gradeEntity;
    private String studentName;
    @ManyToMany(mappedBy = "students", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceEntity> attendanceRecords;

    public StudentEntity() {
    }

    public StudentEntity(Long studenId, GradeEntity gradeEntity, String studentName) {
        this.studenId = studenId;
        this.gradeEntity = gradeEntity;
        this.studentName = studentName;
    }

    public Long getStudenId() {
        return studenId;
    }

    public void setStudenId(Long studenId) {
        this.studenId = studenId;
    }

    public GradeEntity getGradeEntity() {
        return gradeEntity;
    }

    public void setGradeEntity(GradeEntity gradeEntity) {
        this.gradeEntity = gradeEntity;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
