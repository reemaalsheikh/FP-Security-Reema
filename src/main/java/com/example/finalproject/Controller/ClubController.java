package com.example.finalproject.Controller;

import com.example.finalproject.Model.Club;
import com.example.finalproject.Model.Review;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/club")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/get")
    public ResponseEntity getAllClubs() {
        return ResponseEntity.status(200).body(clubService.getAllClubs());
    }

    @PostMapping("/add/{studentId}")
    public ResponseEntity<String> addClub(@PathVariable Integer studentId, @Valid @RequestBody Club club) {
        clubService.addClub(club, studentId);
        return ResponseEntity.ok("Club added successfully");
    }

    @PutMapping("/update/{club_id}")
    public ResponseEntity updateClub(@AuthenticationPrincipal User user,@PathVariable Integer club_id,@Valid @RequestBody Club club) {
        clubService.updateClub(user.getId(),club_id,club);
        return ResponseEntity.status(200).body("Club updated");
    }

    @DeleteMapping("/delete/{club_id}")
    public ResponseEntity deleteClub(@AuthenticationPrincipal User user,@PathVariable Integer club_id) {
        clubService.deleteClub(user.getId(),club_id);
        return ResponseEntity.status(200).body("Club deleted");
    }



    @PutMapping("/{club_id}/assign/{student_id}")
    public ResponseEntity joinClub(@PathVariable Integer club_id, @PathVariable Integer student_id) {
        clubService.joinClub(club_id,student_id);
        return ResponseEntity.status(200).body("join done");
    }

    /*Renad*/
    @GetMapping("/get/club/{name}")
    public ResponseEntity searchClubByName(@AuthenticationPrincipal User user, @PathVariable String name){
        return ResponseEntity.status(200).body(clubService.searchClubByName(user.getId(),name));
    }

    /*Renad*/
    @GetMapping("/get/details/{club_id}")
    public ResponseEntity getClubDetails(@AuthenticationPrincipal User user,@PathVariable Integer club_id){
        return ResponseEntity.status(200).body(clubService.getClubDetails(user.getId(),club_id));
    }
    /*Renad*/
    @GetMapping("/students/{club_id}")
    public ResponseEntity<List<Student>> getStudentsInClub(@AuthenticationPrincipal User user, @PathVariable Integer club_id) {
        List<Student> students=clubService.getStudentsInClub(user.getId(),club_id);
        return ResponseEntity.ok(students);
    }
    /*Renad*/
    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<Club>> getClubMaxCapacity(@AuthenticationPrincipal User user,@PathVariable int capacity) {
        List<Club> clubs=clubService.getClubMaxCapacity(user.getId(),capacity);
        return ResponseEntity.ok(clubs);
    }

}
