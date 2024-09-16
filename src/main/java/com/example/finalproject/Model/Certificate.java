package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String certificateDetails; // This could include the course name, date...

    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate certificateIssuedDate;


    @ManyToOne
    @JsonIgnore
   // @JoinColumn(name = "tutor_id")
    private Tutor tutor;


}
