package com.example.finalproject.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull(message = "Price should not be null!")
    private double price;


    //Relations

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "session_id")// like document to session
    private Session session;


    //reema.. to let the student assign to doc
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_user_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "tutor_id")  // to link documents to tutors
    private Tutor tutor; // Omar added this


}