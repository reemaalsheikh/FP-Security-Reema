package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Session;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.finalproject.Service.SessionService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/get-all")
    public ResponseEntity getAllSessions(){
        return ResponseEntity.status(200).body(sessionService.getAllSessions());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMySessions(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(sessionService.getMySessions(user.getId()));
    }

    //Reema .. assign session to course by tutor
    @PostMapping("/add-session-by-tutor/{course_id}")
    public ResponseEntity addSessionByTutor(@AuthenticationPrincipal User user, @RequestBody @Valid Session session, @PathVariable Integer course_id){
        sessionService.addSession(session,course_id,user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Session Created successfully"));
    }
    //Reema .. assign session to course by admin
    @PostMapping("/add-session-by-admin/{course_id}/{tutor_id}")
    public ResponseEntity addSessionByAdmin( @RequestBody @Valid Session session, @PathVariable Integer course_id, @PathVariable Integer tutor_id){
        sessionService.addSession(session,course_id,tutor_id);
        return ResponseEntity.status(201).body(new ApiResponse("Session Created successfully"));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateSession(@PathVariable Integer id, @RequestBody @Valid Session session, @AuthenticationPrincipal User user) {
        sessionService.updateSession(id, session, user);
        return ResponseEntity.status(200).body(new ApiResponse("Session updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteSession(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        sessionService.deleteSession(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Session deleted successfully"));
    }


    //Reema
    @PutMapping("/assignSessionToStudent/{session_id}")
    public ResponseEntity assignSessionToStudent (@AuthenticationPrincipal User user,@PathVariable Integer session_id){
        sessionService.assignSessionToStudent(session_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Session Assigned to student successfully"));
    }


    @PutMapping("/start/{id}")
    public ResponseEntity<ApiResponse> startSession(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        sessionService.startSession(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Session started successfully"));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse> cancelSession(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        sessionService.cancelSession(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Session canceled successfully"));
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<ApiResponse> endSession(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        sessionService.endSession(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Session completed successfully"));
    }

    @PutMapping("/block/{sessionId}/{studentId}")
    public ResponseEntity<ApiResponse> blockStudentFromSession(@PathVariable Integer sessionId, @PathVariable Integer studentId, @AuthenticationPrincipal User user) {
        sessionService.blockStudentFromSession(sessionId, studentId, user);
        return ResponseEntity.status(200).body(new ApiResponse("Student blocked from session successfully"));
    }

    @GetMapping("/students/{sessionId}")
    public ResponseEntity<Set<Student>> getStudentsInSession(@PathVariable Integer sessionId, @AuthenticationPrincipal User user) {
        Set<Student> students = sessionService.getStudentsInSession(sessionId, user);
        return ResponseEntity.status(200).body(students);
    }

    @GetMapping("/max-participants/{maxParticipants}")
    public ResponseEntity<List<Session>> getSessionsByMaxParticipants(@PathVariable int maxParticipants) {
        List<Session> sessions = sessionService.getSessionsByMaxParticipants(maxParticipants);
        return ResponseEntity.status(200).body(sessions);
    }
}
