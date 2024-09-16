package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.*;
import com.example.finalproject.Repository.SessionRepository;
import com.example.finalproject.Repository.StudentRepository;
import com.example.finalproject.Repository.TutorRepository;
import com.example.finalproject.Repository.ZoomMeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoomMeetingService {

    private final ZoomMeetingRepository zoomRepository;
    private final SessionRepository sessionRepository;
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;



    public List<ZoomMeeting> getAllZoomMeetings() {
        return zoomRepository.findAll();
    }

    //Reema
    // Assign Zoom meeting to Session >> authorized by tutor
    public void addZoom(ZoomMeeting zoom,Integer tutor_id,Integer session_id ) {
        Tutor tutor1=tutorRepository.findTutorById(tutor_id);
        if(tutor1==null){
            throw new ApiException("Tutor not found");
        }
        Session session = sessionRepository.findSessionById(session_id);
        if(session == null) {
            throw new ApiException("Session not found");
        }
        if (!session.getTutor().getUser().getId().equals(tutor_id)) {
            throw new ApiException("You can only add Zoom meetings to your own sessions.");
        }
        zoom.setTutor(tutor1);
        zoom.setSession(session);
        zoomRepository.save(zoom);
    }

    public ZoomMeeting updateZoom(Integer id, ZoomMeeting zoom, User user) {
        ZoomMeeting z = zoomRepository.findZoomMeetingByMeetingId(id);
        if (z == null) {
            throw new ApiException("Zoom meeting not found.");
        }
        if (!z.getSession().getTutor().getUser().getId().equals(user.getId())) {
            throw new ApiException("You can only update Zoom meetings for your own sessions.");
        }
        z.setPrice(zoom.getPrice());
        z.setUrl(zoom.getUrl());
        z.setMeetingDate(zoom.getMeetingDate());
        return zoomRepository.save(z);
    }

    public void deleteZoom(Integer id, User user) {
        ZoomMeeting z = zoomRepository.findZoomMeetingByMeetingId(id);
        if (z == null) {
            throw new ApiException("Zoom meeting not found.");
        }
        // Ensure the Zoom meeting belongs to the current tutor
        if (!z.getSession().getTutor().getUser().getId().equals(user.getId())) {
            throw new ApiException("You can only delete Zoom meetings from your own sessions.");
        }
        zoomRepository.deleteById(id);
    }

    //Reema
    //Assign zoom to student >> student
    public void assignZoomToStudent (Integer zoom_id, Integer student_id) {
        ZoomMeeting zoom = zoomRepository.findZoomMeetingByMeetingId(zoom_id);
        if(zoom == null) {
            throw new ApiException("Zoom meeting not found");
        }
        Student student = studentRepository.findStudentById(student_id);
        if (student == null) {
            throw  new ApiException("Student not found");
        }
        if (!student.isEnrolled()){
            throw new ApiException("Student is not enrolled, you can't assign this zoom!");
        }
        zoom.setStudent(student);
        zoomRepository.save(zoom);
    }

}
