package com.webtehnologies.attendance_management_backend.controller;

import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grade")
public class GradeController {
    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GradeEntity>> getAllGrades () {
        List<GradeEntity> grades = gradeService.findAllGrades();
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GradeEntity> getGradeById (@PathVariable("id") Long id) {
        GradeEntity grade = gradeService.findGradeById(id);
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<GradeEntity> addGrade(@RequestBody GradeEntity grade) {
        GradeEntity newGrade = gradeService.addGrade(grade);
        return new ResponseEntity<>(newGrade, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<GradeEntity> updatedGrade(@RequestBody GradeEntity grade) {
        GradeEntity updateGrade = gradeService.updateGrade(grade);
        return new ResponseEntity<>(updateGrade, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrade(@PathVariable("id") Long id) {
        gradeService.deleteGradeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
