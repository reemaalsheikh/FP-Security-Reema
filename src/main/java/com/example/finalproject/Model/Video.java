package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotNull
    @Positive
    private double price;


    //Relations
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "session_id")
    private Session session;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id")
    private Course course;





}