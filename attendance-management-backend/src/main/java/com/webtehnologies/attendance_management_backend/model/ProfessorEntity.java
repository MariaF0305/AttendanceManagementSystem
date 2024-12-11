package com.webtehnologies.attendance_management_backend.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "professor")
public class ProfessorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String subject;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "professor_grade",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "grade_id")
    )
    private List<GradeEntity> grades;

    public ProfessorEntity(List<GradeEntity> grades) {
        this.grades = grades;
    }

    public ProfessorEntity(Long id, String name, String subject, String email, String password, List<GradeEntity> grades) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.email = email;
        this.password = password;
        this.grades = grades;
    }

    public ProfessorEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long professorId) {
        this.id = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String professorName) {
        this.name = professorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String professorEmail) {
        this.email = professorEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String professorPassword) {
        this.password = professorPassword;
    }

    public List<GradeEntity> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeEntity> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Professor Entity{" +
                "professorId=" + id +
                ", professorName='" + name + '\'' +
                ", professorSubject='" + subject + '\'' +
                ", professorEmail='" + email + '\'' +
                ", professorPassword='" + password + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
