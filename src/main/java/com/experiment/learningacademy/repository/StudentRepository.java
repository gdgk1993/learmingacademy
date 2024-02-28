package com.experiment.learningacademy.repository;

import com.experiment.learningacademy.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
