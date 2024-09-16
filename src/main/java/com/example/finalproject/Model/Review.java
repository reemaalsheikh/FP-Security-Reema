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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Comment should be not empty!")
    @Size(max = 50,message = "maximum character in comments is 50")
    @Column(columnDefinition = "varchar(50) not null")
    private String comment;

    @NotNull(message = "rating should be not null!")
    @Positive
    @Column(columnDefinition = "double not null")
    private double rating;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreated;



    //Relations


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;




    //Many Reviews to one course
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;



}
