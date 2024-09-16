package com.example.finalproject.Repository;

import com.example.finalproject.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer> {

    Tutor findTutorById (Integer id);


    @Query("select t from Tutor t where t.hasRecommendations = true")
    Set<Tutor> findTutorByHasRecommendations ();


    Tutor findTutorByFirstName(String firstName);

    List<Tutor> findTutorByGpa(double gpa);
}
