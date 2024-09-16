package com.example.finalproject.Service;

import com.example.finalproject.Api.ApiException;
import com.example.finalproject.Model.Certificate;
import com.example.finalproject.Model.Tutor;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.AuthRepository;
import com.example.finalproject.Repository.CertificateRepository;
import com.example.finalproject.Repository.TutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificateRepository certificateRepository;
    private final TutorRepository tutorRepository;



    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public List<Certificate> getMyCertificate(Integer tutor_id) {
      Tutor tutor = tutorRepository.findTutorById(tutor_id);
      if(tutor == null){
          throw new ApiException("Tutor not found");
      }
      return certificateRepository.findAllByTutor(tutor);
    }



    public void addCertificate(Certificate certificate, Integer tutor_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if(tutor == null){
            throw new ApiException("Tutor not found");
        }

        certificate.setCertificateIssuedDate(LocalDate.now());
        certificate.setTutor(tutor);
        certificateRepository.save(certificate);
    }




    public void updateCertificate(Certificate certificate,Integer tutor_id ,Integer certificate_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if(tutor == null){
            throw new ApiException("Tutor not found");
        }
        Certificate certificate1 = certificateRepository.findCertificateById(certificate_id);
        if (certificate1 == null) {
            throw new ApiException("Certificate not found");
        } else if (certificate1.getTutor().getId() != tutor_id) {
            throw new ApiException("Tutor id mismatch, you don't have the authority");
        }
        certificate1.setCertificateDetails(certificate.getCertificateDetails());
        certificate1.setCertificateIssuedDate(LocalDate.now());

        certificate1.setTutor(tutor);
        certificateRepository.save(certificate1);
    }


    public void deleteCertificate(Integer tutor_id ,Integer certificate_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if(tutor == null){
            throw new ApiException("Tutor not found");
        }
        Certificate certificate1 = certificateRepository.findCertificateById(certificate_id);
        if (certificate1 == null) {
            throw new ApiException("Certificate not found");
        }else if (certificate1.getTutor().getId() != tutor_id) {
            throw new ApiException("Tutor id mismatch, you don't have the authority");
        }

        certificateRepository.delete(certificate1);
    }



    public Certificate issueCertificate (Integer certificate_id,Integer tutor_id) {
        Tutor tutor = tutorRepository.findTutorById(tutor_id);
        if (tutor == null) {
            throw new ApiException("Tutor not found");
        }
        if(!tutor.isHasTakenExam()){
            throw new ApiException("Tutor has not taken the exam");
        }

        Certificate certificate1 = certificateRepository.findCertificateById(certificate_id);
        if (certificate1 == null) {
            throw new ApiException("Certificate not found");
        }
//        else if (certificate1.getTutor().getId() != tutor_id) {
//            throw new ApiException("Tutor id mismatch, you don't have the authority");
//        }

        certificate1.setTutor(tutor);
        certificate1.setCertificateIssuedDate(LocalDate.now());
        tutor.getCertificates().add(certificate1);
        certificate1.setCertificateDetails("Certificate of Achievement" +" "  +
                "This is to certify that "+ " "  + tutor.getFirstName() +" "+tutor.getLastName() + " " +
                "has successfully completed the necessary requirements" +" "+
                "Issued on: " + LocalDate.now() + "  " +  "  "+
                "Congratulations on your achievement!");
        certificateRepository.save(certificate1);
        tutorRepository.save(tutor);

        return certificate1;
    }




}
