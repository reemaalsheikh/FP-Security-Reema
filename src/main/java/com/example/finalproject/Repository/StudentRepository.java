package com.example.finalproject.Repository;

import com.example.finalproject.Model.Certificate;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findStudentById(Integer id);

}
