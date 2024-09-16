package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.User;
import com.example.finalproject.Model.ZoomMeeting;
import com.example.finalproject.Service.ZoomMeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/zoom")
@RequiredArgsConstructor
public class ZoomMeetingController {
    private final ZoomMeetingService zoomService;

    @GetMapping("/get")
    public ResponseEntity getZooms(){
        return ResponseEntity.status(200).body(zoomService.getAllZoomMeetings());
    }

    //Reema
    @PostMapping("/add/{session_id}")
    public ResponseEntity addZoom(@AuthenticationPrincipal User user, @Valid @RequestBody ZoomMeeting zoom,@PathVariable Integer session_id) {
        zoomService.addZoom(zoom,session_id, user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Zoom Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateZoom(@PathVariable Integer id, @Valid @RequestBody ZoomMeeting zoom, @AuthenticationPrincipal User user) {
        zoomService.updateZoom(id, zoom, user);
        return ResponseEntity.status(200).body(new ApiResponse("Zoom Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteZoom(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        zoomService.deleteZoom(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Zoom Deleted"));
    }

    @PutMapping("/assignZoomToStudent/{zoom_id}")
    public ResponseEntity assignZoomToStudent(@AuthenticationPrincipal User user,@PathVariable Integer zoom_id){
        zoomService.assignZoomToStudent(zoom_id,user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Zoom meeting Assigned to student successfully"));
    }
}