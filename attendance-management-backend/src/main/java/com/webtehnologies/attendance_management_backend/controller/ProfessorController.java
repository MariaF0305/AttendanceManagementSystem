package com.webtehnologies.attendance_management_backend.controller;

import com.webtehnologies.attendance_management_backend.dto.LoginRequest;
import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.model.ProfessorEntity;
import com.webtehnologies.attendance_management_backend.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/professor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {
    private ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfessorEntity>> getAllProfessors() {
        List<ProfessorEntity> professors = professorService.findAllProfessors();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfessorEntity> getProfessorById(@PathVariable("id") Long id) {
        ProfessorEntity professor = professorService.findProfessorById(id);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProfessorEntity> addProfessor(@RequestBody ProfessorEntity professor) {
        ProfessorEntity newProfessor = professorService.addProfessor(professor);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfessorEntity> updatedProfessor(@RequestBody ProfessorEntity professor) {
        ProfessorEntity updateProfessor = professorService.updateProfessor(professor);
        return new ResponseEntity<>(updateProfessor, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfessor(@PathVariable("id") Long id) {
        professorService.deleteProfessorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/assign-grade/{professorId}/{gradeId}")
    public ResponseEntity<?> assignProfessorToGrade(@PathVariable("professorId") Long professorId, @PathVariable("gradeId") Long gradeId) {
        ProfessorEntity updateProfessor = professorService.assignGradeToProfessor(professorId, gradeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/professor-grades/{professorId}")
    public ResponseEntity<List<GradeEntity>> getProfessorId(@PathVariable("professorId") Long professorId) {
        List<GradeEntity> grades = professorService.findGradesByProfessorId(professorId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginProfessor(@RequestBody LoginRequest loginRequest) {
        try {
            ProfessorEntity professor = professorService.loginProfessor(loginRequest.getEmail(), loginRequest.getPassword());
            Map<String, Object> response = new HashMap<>();
            response.put("id", professor.getId());
            response.put("email", professor.getEmail());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid email or password"));
        }
    }
}
