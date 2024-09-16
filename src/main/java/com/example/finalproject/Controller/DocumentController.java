package com.example.finalproject.Controller;

import com.example.finalproject.Api.ApiResponse;
import com.example.finalproject.Model.Document;
import com.example.finalproject.Model.User;
import com.example.finalproject.Service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/get")
    public ResponseEntity getDocuments(){
        return ResponseEntity.status(200).body(documentService.getAllDocuments());
    }

    //Reema
    @PostMapping("/add/{session_id}")
    public ResponseEntity addDocument(@AuthenticationPrincipal User user , @Valid @RequestBody Document document, @PathVariable Integer session_id) {
        documentService.addDocument(document,session_id, user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("Document Added Successfully"));
    }

    @PutMapping("/update/{documentId}")
    public ResponseEntity updateDocument(@PathVariable Integer documentId, @Valid @RequestBody Document document, @AuthenticationPrincipal User user) {
        documentService.updateDocument(user.getId(), documentId, document);
        return ResponseEntity.status(200).body(new ApiResponse("Document updated successfully"));
    }

    @DeleteMapping("/delete/{documentId}")
    public ResponseEntity deleteDocument(@PathVariable Integer documentId, @AuthenticationPrincipal User user) {
        documentService.deleteDocument(user.getId(), documentId);
        return ResponseEntity.status(200).body(new ApiResponse("Document deleted successfully"));
    }

    //Reema
    @PutMapping("/assignDocumentToStudent/{doc_id}")
    public ResponseEntity assignDocumentToStudent(@AuthenticationPrincipal User user ,@PathVariable Integer doc_id){
        documentService.assignDocToStudent(doc_id, user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Documents Assigned to student successfully"));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Document> getDocumentByTitle(@PathVariable String title) {
        Document document = documentService.getDocumentByTitle(title);
        return ResponseEntity.status(200).body(document);
    }

}