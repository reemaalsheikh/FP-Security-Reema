package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Certificate;
import com.example.finalproject.Model.User;
import com.example.finalproject.Repository.CertificateRepository;
import com.example.finalproject.Service.CertificateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/certificate")
@RequiredArgsConstructor
public class CertificateController {
    private final CertificateService certificateService;

    @GetMapping("/get-all")
    public ResponseEntity getAllCertificates() {
        return ResponseEntity.status(200).body(certificateService.getAllCertificates());
    }

    @GetMapping("/get-my")
    public ResponseEntity getMyCertificates(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(certificateService.getMyCertificate(user.getId()));
    }


    @PostMapping("/add")
    public ResponseEntity addCertificate (@AuthenticationPrincipal User user,@Valid @RequestBody Certificate certificate) {
        certificateService.addCertificate(certificate, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Certificate added successfully"));
    }



    @PutMapping("/update/{certificate_id}")
    public ResponseEntity updateCertificate (@AuthenticationPrincipal User user,@PathVariable Integer certificate_id ,@Valid @RequestBody Certificate certificate) {
        certificateService.updateCertificate(certificate, user.getId(), certificate_id);
        return ResponseEntity.status(200).body(new ApiResponse("Certificate updated successfully"));
    }


    @DeleteMapping("/delete/{certificate_id}")
    public ResponseEntity deleteCertificate (@AuthenticationPrincipal User user ,@PathVariable Integer certificate_id) {
        certificateService.deleteCertificate(certificate_id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Certificate deleted successfully"));
    }


    @GetMapping("/issueCertificate/{certificate_id}")
    public ResponseEntity issueCertificate (@AuthenticationPrincipal User user ,@PathVariable Integer certificate_id){
        return ResponseEntity.status(200).body(certificateService.issueCertificate(certificate_id,user.getId()));
    }








}
