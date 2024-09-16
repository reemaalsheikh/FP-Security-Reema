package com.example.finalproject.Service;
import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Exam;
import com.example.finalproject.Model.Tutor;
import com.example.finalproject.Repository.ExamRepository;
import com.example.finalproject.Repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final TutorRepository tutorRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }


    public void addExam(Integer auth_id,Exam exam) {
        Tutor tutor=tutorRepository.findTutorById(auth_id);
        if(tutor==null){
            throw new ApiException("Tutor not found");
        }
        exam.setTutor(tutor);
        examRepository.save(exam);
    }

    public void updateExam(Integer auth_id,Integer exam_id,Exam exam) {
        Tutor tutor=tutorRepository.findTutorById(auth_id);
        if(tutor==null){
            throw new ApiException("Tutor not found");
        }
        Exam oldExam=examRepository.findExamById(exam_id);
        if(oldExam==null){
            throw new ApiException("Exam not found");

        }else if(oldExam.getTutor().getId()!=auth_id){
            throw new ApiException("sorry you don't have authority");
        }
        oldExam.setName(exam.getName());
        oldExam.setScore(exam.getScore());
        oldExam.setMaxScore(exam.getMaxScore());
        oldExam.setDateTaken(LocalDate.now());
        oldExam.setTutor(tutor);
        examRepository.save(oldExam);
    }

    public void deleteExam(Integer auth_id,Integer exam_id) {
        Tutor tutor=tutorRepository.findTutorById(auth_id);
        if(tutor==null){
            throw new ApiException("Tutor not found");
        }
        Exam oldExam=examRepository.findExamById(exam_id);
        if(oldExam==null){
            throw new ApiException("Exam not found");

        }else if(oldExam.getTutor().getId()!=auth_id){
            throw new ApiException("sorry you don't have authority");
        }
        examRepository.delete(oldExam);
    }



    //Reema
    //Assign exam to tutor
    public void assignExamToTutor(Integer tutor_id, Integer exam_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if (tutor == null) {
            throw new ApiException("Tutor not found");
        }
        Exam exam= examRepository.findExamById(exam_id);
        if (exam == null) {
            throw new ApiException("Exam not found");
        }
        if(tutor.getExams().contains(exam)){
            throw new ApiException("Tutor already assigned to exam");
        }
        exam.setDateTaken(LocalDate.now());
        tutor.setHasTakenExam(true);

        exam.setTutor(tutor);
        examRepository.save(exam);
    }

}
