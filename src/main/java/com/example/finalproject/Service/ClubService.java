package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Club;
import com.example.finalproject.Model.Student;
import com.example.finalproject.Repository.ClubRepository;
import com.example.finalproject.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final StudentRepository studentRepository;

    public List<Club> getAllClubs(){
        return clubRepository.findAll();
    }
    //Student
    public void addClub(Club club, Integer studentId) {
        Student student = studentRepository.findStudentById(studentId);
        if (student == null) {
            throw new ApiException("Student not found");
        }
        // Set the leader of the club
        club.setLeader(student);

        // Save the club
        clubRepository.save(club);

        // Add the club to the student's list of clubs
        student.getClubs().add(club);
        studentRepository.save(student);
    }

    public void updateClub(Integer auth_id,Integer club_id,Club club){
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }
        Club oldClub=clubRepository.findClubById(club_id);
        if(oldClub==null){
            throw new ApiException("Club not found");

        }else if(oldClub.getLeader().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        oldClub.setName(club.getName());
        oldClub.setLocation(club.getLocation());
        oldClub.setDescription(club.getDescription());
        oldClub.setCapacity(club.getCapacity());
        oldClub.setLeader(club.getLeader());
        clubRepository.save(oldClub);
    }

    public void deleteClub(Integer auth_id,Integer club_id){
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }
        Club oldClub=clubRepository.findClubById(club_id);
        if(oldClub==null){
            throw new ApiException("Club not found");

        }else if(oldClub.getLeader().getId()!=auth_id){
            throw new ApiException("sorry you dont have authority");
        }
        clubRepository.delete(oldClub);
    }


    /*Renad*/
    public void joinClub(Integer club_id,Integer student_id){
        Club club=clubRepository.findClubById(club_id);
        Student student=studentRepository.findStudentById(student_id);
        if(club==null || student==null){
            throw new ApiException("Club not assign");
        }
        club.getStudents().add(student);
        student.getClubs().add(club);

        clubRepository.save(club);
        studentRepository.save(student);
    }

    /*Renad*/
    public Club searchClubByName(Integer auth_id,String name) {
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }
        Club club1=clubRepository.findClubByName(name);
        if (club1 == null) {
            throw  new ApiException("Club not found");
        }
        return club1;
    }

    /*Renad*/
    public Club getClubDetails(Integer auth_id,Integer club_id) {
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }
        Club club1=clubRepository.findClubById(club_id);
        if (club1 == null) {
            throw  new ApiException("Club not found");
        }
        return club1;
    }
    /*Renad*/
    public List<Student> getStudentsInClub(Integer auth_id,Integer club_id) {
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }

        Club club1=clubRepository.findClubById(club_id);
        if (club1 == null) {
            throw  new ApiException("Club not found");
        }
        return club1.getStudents();
    }

    /*Renad*/
    public List<Club> getClubMaxCapacity(Integer auth_id,int capacity) {
        Student student=studentRepository.findStudentById(auth_id);
        if(student==null) {
            throw new ApiException("Student not found");
        }
        return clubRepository.findAllByCapacity(capacity);
    }


}
