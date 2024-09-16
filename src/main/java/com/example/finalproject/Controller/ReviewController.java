package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Review;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;


    @GetMapping("/get")
    public ResponseEntity getAllReviews() {
        return ResponseEntity.status(200).body(reviewService.getAllReviews());
    }

    @PostMapping("/add")
    public ResponseEntity addReview(@Valid @RequestBody Review review) {
        reviewService.addReview(review);
        return ResponseEntity.status(200).body(new ApiResponse("Review added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id,@Valid @RequestBody Review review) {
        reviewService.updateReview(id, review);
        return ResponseEntity.status(200).body(new ApiResponse("Review updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.status(200).body(new ApiResponse("Review deleted successfully"));
    }

    //Reema
    @PostMapping("/assignReviewToTutor/{tutor_id}")
    public ResponseEntity assignReviewToTutor (@AuthenticationPrincipal User user,@Valid @RequestBody Review review,@PathVariable Integer tutor_id){
        reviewService.assignReviewToTutor(review,user.getId(),tutor_id);
        return ResponseEntity.status(200).body(new ApiResponse("Review assigned to tutor successfully!"));
    }

    //Reema
    @PostMapping("/assignReviewToCourse/{course_id}")
    public ResponseEntity assignReviewToCourse (@AuthenticationPrincipal User user, @Valid @RequestBody Review review,@PathVariable Integer course_id){
        reviewService.assignReviewToCourse(review,user.getId(),course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Review assigned to course successfully!"));
    }

}
