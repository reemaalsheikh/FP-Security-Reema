package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Course;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses(){
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    //Reema
    @GetMapping("/get-My-Courses")
    public ResponseEntity getMyCourses(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(courseService.getMyCourses(user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity addCourse(@AuthenticationPrincipal User user, @Valid @RequestBody Course course){
        courseService.addCourse(course, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully"));
    }

    @PutMapping("/update/{course_id}")
    public ResponseEntity updateCourse(@AuthenticationPrincipal User user,@PathVariable Integer course_id,@Valid @RequestBody Course course){
        courseService.updateCourse(course, user.getId(),course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully!"));
    }

    @DeleteMapping("/delete/{course_id}")
    public ResponseEntity deleteCourse(@AuthenticationPrincipal User user,@PathVariable Integer course_id){
        courseService.deleteCourse(course_id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully!"));
    }

    @GetMapping("/courseFilter/{minPrice}/{maxPrice}")
    public ResponseEntity CourseFilter(@PathVariable double minPrice ,@PathVariable double maxPrice){
        return ResponseEntity.status(200).body(courseService.courseFilter(minPrice,maxPrice));
    }


    @GetMapping("/findCoursesByLearningMethod/{learningMethod}")
    public ResponseEntity findCoursesByLearningMethod (@PathVariable String learningMethod){
        return ResponseEntity.status(200).body(courseService.findCoursesByLearningMethod(learningMethod));
    }

    @GetMapping("/mostPopularCourses")
    public ResponseEntity getMostPopularCourses(){
        return ResponseEntity.status(200).body(courseService.getMostPopularCourses());
    }






    /*Renad*/
    @GetMapping("/get/course/{course_id}")
    public ResponseEntity enrolledStudentsWithCourse(@PathVariable Integer course_id){
        List<Student> students= courseService.enrolledStudentsWithCourse(course_id);
        return ResponseEntity.status(200).body(students);
    }
    /*Renad*/
    @GetMapping("/get/tutor/{tutor_id}")
    public ResponseEntity enrolledStudentsWithTutor(@PathVariable Integer tutor_id){
        List<Student> students= courseService.enrolledStudentsWithTutor(tutor_id);
        return ResponseEntity.status(200).body(students);
    }
    /*Renad*/
    @GetMapping("/get/subject/{subject}")
    public ResponseEntity searchCourse(@PathVariable String subject){
        return ResponseEntity.status(200).body(courseService.searchCourse(subject));
    }
    /*Renad*/
    @GetMapping("/get/details/{course_id}")
    public ResponseEntity getCourseDetails(@PathVariable Integer course_id){
        return ResponseEntity.status(200).body(courseService.getCourseDetails(course_id));
    }

    /*Renad*/
    @GetMapping("/get/course/reviews/{course_id}/")
    public ResponseEntity getCourseReviews(@PathVariable Integer course_id,@PathVariable Integer review_id){
        return ResponseEntity.status(200).body(courseService.getCourseReviews(course_id));
    }



}
