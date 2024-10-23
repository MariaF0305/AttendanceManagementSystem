package com.webtehnologies.attendance_management_backend.service;

import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.repository.GradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GradeService {
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public GradeEntity addGrade(GradeEntity grade) {
        return gradeRepository.save(grade);
    }

    public GradeEntity updateGrade(GradeEntity grade) {
        Optional<GradeEntity> existingGrade = gradeRepository.findById(grade.getGradeId());
        if (existingGrade.isPresent()) {
            return gradeRepository.save(grade);
        } else {
            throw new RuntimeException("Grade not found");
        }
    }


    public GradeEntity findGradeById(Long id) {
        Optional<GradeEntity> grade = gradeRepository.findById(id);
        return grade.orElseThrow(() -> new RuntimeException("Grade not found")); // Handle not found
    }

    public List<GradeEntity> findAllGrades() {
        return gradeRepository.findAll();
    }

    public void deleteGradeById(Long id) {
        gradeRepository.deleteById(id);
    }
}
