package com.example.finalproject.Repository;

import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

    Video findVideoById(Integer id);

    List<Video> findAllByCourse(Course course);

    List<Video> findAllByPriceBetween(double minPrice, double maxPrice);

    List<Video> findAllByTitleContainingIgnoreCase(String keyword);

}
