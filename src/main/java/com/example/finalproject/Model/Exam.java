package com.example.finalproject.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name should not be empty!")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotNull(message = "grade should be not null!")
    @Column(columnDefinition = "double not null")
    private double score;

    @NotNull(message = "maxScore should be not null!")
    @Column(columnDefinition = "double not null")
    private double maxScore;


    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTaken;


    //Relations


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
