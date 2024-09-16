package com.example.finalproject.Repository;
import com.example.finalproject.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Document findDocumentById(Integer id);

//Omar
     Document findDocumentByTitle(String title);
    List<Document> findAllByTutorId(Integer tutorId);
}