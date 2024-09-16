package com.example.finalproject.Repository;

import com.example.finalproject.Model.Review;
import com.example.finalproject.Model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

    List<Review> findReviewByTutor(Tutor tutor);
}
