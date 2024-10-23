package com.webtehnologies.attendance_management_backend.repository;

import com.webtehnologies.attendance_management_backend.model.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

}
