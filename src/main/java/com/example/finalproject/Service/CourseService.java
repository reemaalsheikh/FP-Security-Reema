package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TutorRepository tutorRepository;
    private final AuthRepository authRepository;


    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


//Reema
    public List<Course> getMyCourses(Integer tutor_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if(tutor == null) {
            throw new ApiException("Tutor not found");
        }
        return courseRepository.findAllByTutor(tutor);
    }

    //Reema
    //assign course to tutor
    public void addCourse(Course course, Integer tutor_id) {
    Tutor tutor = tutorRepository.findTutorById(tutor_id);
    if (tutor == null) {
        throw  new ApiException("Tutor not found");
    }
    if(!tutor.isHasTakenExam()){
        throw  new ApiException("Tutor has not taken exam, you can't add this course!");
    }

    course.setTutor(tutor);
    courseRepository.save(course);

    }

    public void updateCourse(Course course,Integer tutor_id ,Integer course_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if (tutor == null) {
            throw  new ApiException("Tutor not found");
        }

        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw  new ApiException("Course not found");
        }
        course1.setName(course.getName());
        course1.setDescription(course.getDescription());
        course1.setDuration(course.getDuration());
        course1.setLearningMethod(course.getLearningMethod());
        course1.setSubject(course.getSubject());
        course1.setPrice(course.getPrice());
        courseRepository.save(course1);

    }

    public void deleteCourse(Integer tutor_id,Integer course_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if (tutor == null) {
            throw  new ApiException("Tutor not found");
        }
        Course course = courseRepository.findCourseById(course_id);
        if (course == null) {
            throw  new ApiException("Course not found");
        }
        else if (course.getTutor().getId() != tutor_id) {
            throw  new ApiException("Tutor id mismatch, u don't have authority");
        }
        courseRepository.delete(course);
    }


    //Reema
    public List<Course> courseFilter (double minPrice , double maxPrice) {
        List<Course> courses = courseRepository.findCourseByPriceBetween(minPrice, maxPrice);
        if (courses.isEmpty()) {
            throw  new ApiException("Courses not found");
        }
     return courses;
    }


   //Reema
    public List<Course> findCoursesByLearningMethod (String learningMethod) {
        List<Course> courses = courseRepository.findCoursesByLearningMethod(learningMethod);
        if (courses.isEmpty()) {
            throw  new ApiException("Courses not found");
        }
        return courses;
    }

    //Reema
    public List<Course> getMostPopularCourses() {
        List<Course> popularCourses = courseRepository.findMostPopularCourse();

        if (popularCourses.isEmpty()) {
            throw  new ApiException("No popular courses found");
        }
        return popularCourses;
    }



    /*Renad*/
    public List<Student> enrolledStudentsWithCourse(Integer course_id) {
        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw  new ApiException("Course not found");
        }
        List<Student> students =course1.getStudents().stream().toList();
        return students;
    }

    /*Renad*/
    public List<Student> enrolledStudentsWithTutor(Integer tutor_id) {
        Course course1=courseRepository.findCourseByTutorId(tutor_id);
        if (course1 == null) {
            throw  new ApiException("Tutor not found");
        }
        List<Student> students = course1.getStudents().stream().toList();
        return students;
    }
    /*Renad*/
    public Course searchCourse(String subject) {
        Course course1=courseRepository.findCourseBySubject(subject);
        if (course1 == null) {
            throw  new ApiException("Course by this subject not found");
        }
        return course1;
    }
    /*Renad*/
    public Course getCourseDetails(Integer course_id) {
        Course course1 = courseRepository.findCourseById(course_id);
        if (course1 == null) {
            throw  new ApiException("Course not found");
        }
        return course1;
    }

    /*Renad*/
    public List<Review> getCourseReviews(Integer course_id) {
        Course course1 = courseRepository.findCourseById(course_id);
        if(course1==null) {
            throw new ApiException("Course not found");
        }
        return course1.getReview().stream().toList();
    }




}
