package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.User;
import com.example.finalproject.Model.Video;
import com.example.finalproject.Service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/get")
    public ResponseEntity getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.status(200).body(videos);
    }

    @PostMapping("/add/{session_id}")
    public ResponseEntity addVideo(@AuthenticationPrincipal User user, @Valid @RequestBody Video video, @PathVariable Integer session_id) {
        videoService.addVideo(video,session_id, user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Video Added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateVideo(@PathVariable Integer id, @Valid @RequestBody Video video, @AuthenticationPrincipal User user) {
        videoService.updateVideo(id, video, user);
        return ResponseEntity.status(200).body(new ApiResponse("Video updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVideo(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        videoService.deleteVideo(id, user);
        return ResponseEntity.status(200).body(new ApiResponse("Video deleted successfully"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getVideoById(@PathVariable Integer id, @AuthenticationPrincipal User user) {
        Video video = videoService.getVideoById(id, user);
        return ResponseEntity.status(200).body(video);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity getVideosByCourseId(@PathVariable Integer courseId, @AuthenticationPrincipal User user) {
        List<Video> videos = videoService.getVideosByCourseId(courseId, user);
        return ResponseEntity.status(200).body(videos);
    }


    @GetMapping("/priceRange/{minPrice}/{maxPrice}")
    public ResponseEntity getVideosByPriceRange(@PathVariable double minPrice, @PathVariable double maxPrice) {
        List<Video> videos = videoService.getVideosByPriceRange(minPrice, maxPrice);
        return ResponseEntity.status(200).body(videos);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity searchVideosByTitle(@PathVariable String keyword) {
        List<Video> videos = videoService.searchVideosByTitle(keyword);
        return ResponseEntity.status(200).body(videos);
    }

    @PutMapping("/increasePrice/{id}")
    public ResponseEntity increaseVideoPrice(@PathVariable Integer id, @RequestParam double percentage, @AuthenticationPrincipal User user) {
        videoService.increaseVideoPriceByPercentage(id, percentage, user);
        return ResponseEntity.status(200).body(new ApiResponse("Video price increased successfully"));
    }

    @PutMapping("/decreasePrice/{id}")
    public ResponseEntity decreaseVideoPrice(@PathVariable Integer id, @RequestParam double percentage, @AuthenticationPrincipal User user) {
        videoService.decreaseVideoPriceByPercentage(id, percentage, user);
        return ResponseEntity.status(200).body(new ApiResponse("Video price decreased successfully"));
    }

    @DeleteMapping("/course/{courseId}/delete")
    public ResponseEntity deleteVideosByCourseId(@PathVariable Integer courseId, @AuthenticationPrincipal User user) {
        videoService.deleteVideosByCourseId(courseId, user);
        return ResponseEntity.status(200).body(new ApiResponse("All videos for the course deleted successfully"));
    }
}

