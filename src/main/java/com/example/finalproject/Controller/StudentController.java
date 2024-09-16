package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.DTO.StudentDTO;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        return ResponseEntity.status(200).body(studentService.getAllStudents());
    }

    @PostMapping("/register")
    public ResponseEntity studentRegister(@RequestBody @Valid StudentDTO studentDTO){
        studentService.studentRegister(studentDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Student registered successfully!"));

    }
    @PutMapping("/update")
    public ResponseEntity updateStudent(@AuthenticationPrincipal User user, @RequestBody @Valid StudentDTO studentDTO ){
       studentService.updateStudent(studentDTO, user.getId());
       return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully!"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@AuthenticationPrincipal User user){
        studentService.deleteStudent(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully!"));
    }

    @PutMapping("/studentEnrollment/{course_id}")
    public ResponseEntity studentEnrollment(@AuthenticationPrincipal User user, @PathVariable Integer course_id ){
        studentService.studentEnrollment(user.getId(), course_id);
        return ResponseEntity.status(200).body(new ApiResponse("Student enrolled successfully!"));
    }




}
