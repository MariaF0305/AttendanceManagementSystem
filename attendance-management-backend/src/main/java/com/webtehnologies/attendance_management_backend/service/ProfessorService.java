package com.webtehnologies.attendance_management_backend.service;

import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import com.webtehnologies.attendance_management_backend.model.ProfessorEntity;
import com.webtehnologies.attendance_management_backend.repository.GradeRepository;
import com.webtehnologies.attendance_management_backend.repository.ProfessorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private GradeRepository gradeRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, GradeRepository gradeRepository) {
        this.professorRepository = professorRepository;
        this.gradeRepository = gradeRepository;
    }

    public ProfessorEntity addProfessor(ProfessorEntity professor) {
        return professorRepository.save(professor);
    }

    public ProfessorEntity updateProfessor(ProfessorEntity professor) {
        Optional<ProfessorEntity> existingProfessor = professorRepository.findById(professor.getId());
        if (existingProfessor.isPresent()) {
            return professorRepository.save(professor);
        } else {
            throw new RuntimeException("Professor not found");
        }
    }

    public ProfessorEntity findProfessorById(Long id) {
        Optional<ProfessorEntity> professor = professorRepository.findById(id);
        return professor.orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public List<ProfessorEntity> findAllProfessors() {
        return professorRepository.findAll();
    }

    public void deleteProfessorById(Long id) {
        professorRepository.deleteById(id);
    }

    public ProfessorEntity assignGradeToProfessor(Long professorId, Long gradeId) {
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        GradeEntity grade = gradeRepository.findById(gradeId)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
        if (professor.getGrades().contains(grade)) {
            throw new RuntimeException("Professor already assigned to grade");
        }
        professor.getGrades().add(grade);
        return professorRepository.save(professor);
    }

    public List<GradeEntity> findGradesByProfessorId(Long professorId) {
        ProfessorEntity professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        return professor.getGrades();
    }

    public ProfessorEntity loginProfessor(String email, String password) {
        Optional<ProfessorEntity> professorOpt = professorRepository.findByEmail(email);

        if (professorOpt.isEmpty() || !professorOpt.get().getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        return professorOpt.get();
    }
}