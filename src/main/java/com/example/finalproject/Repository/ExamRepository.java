package com.example.finalproject.Repository;

import com.example.finalproject.Model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    Exam findExamById(Integer id);
}
