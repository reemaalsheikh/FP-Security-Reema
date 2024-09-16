package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Exam;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam")
public class ExamController {

    private final ExamService examService;


    @GetMapping("/get")
    public ResponseEntity getAllExams() {
        return ResponseEntity.status(200).body(examService.getAllExams());
    }

    @PostMapping("/add")
    public ResponseEntity addExam(@AuthenticationPrincipal User user, @Valid @RequestBody Exam exam) {
        examService.addExam(user.getId(),exam);
        return ResponseEntity.status(200).body("Exam added");
    }

    @PutMapping("/update/{exam_id}")
    public ResponseEntity updateExam(@AuthenticationPrincipal User user,@PathVariable Integer exam_id,@Valid @RequestBody Exam exam) {
        examService.updateExam(user.getId(),exam_id, exam);
        return ResponseEntity.status(200).body("Exam updated");
    }

    @DeleteMapping("/delete/{exam_id}")
    public ResponseEntity deleteExam(@AuthenticationPrincipal User user,@PathVariable Integer exam_id) {
        examService.deleteExam(user.getId(),exam_id);
        return ResponseEntity.status(200).body("Exam deleted");
    }

    //Reema
    @PutMapping("/assignExamToTutor/{exam_id}")
    public ResponseEntity assignExamToTutor(@AuthenticationPrincipal User user, @PathVariable Integer exam_id) {
        examService.assignExamToTutor(user.getId(), exam_id);
        return ResponseEntity.status(200).body(new ApiResponse("Exam assigned to tutor successfully"));
    }

}
