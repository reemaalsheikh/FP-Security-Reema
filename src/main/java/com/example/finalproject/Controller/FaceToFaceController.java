package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.FaceToFace;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.FaceToFaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/face")
@RequiredArgsConstructor
public class FaceToFaceController {

    private final FaceToFaceService faceService;

    @GetMapping("/get")
    public ResponseEntity getAllFaceMeetings() {
        return ResponseEntity.status(200).body(faceService.getAllFaceMeeting());
    }

    @PostMapping("/AssignFaceMeetingToSession/{session_id}")
    public ResponseEntity AssignFaceMeetingToSession(@AuthenticationPrincipal User user, @PathVariable Integer session_id, @Valid @RequestBody FaceToFace face) {
        faceService.AssignFaceMeetingToSession(face,session_id, user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Face Added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateFaceMeeting(@PathVariable Integer id, @Valid @RequestBody FaceToFace face, @AuthenticationPrincipal User user) {
        faceService.updateFaceMeeting(id, face, user);
        return ResponseEntity.status(200).body(new ApiResponse("Face-to-face meeting updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteFaceMeeting(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        faceService.deleteFaceMeeting(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Face-to-face meeting deleted successfully"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<FaceToFace> getFaceById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        FaceToFace face = faceService.findFaceById(id, user);
        return ResponseEntity.status(200).body(face);
    }
}
