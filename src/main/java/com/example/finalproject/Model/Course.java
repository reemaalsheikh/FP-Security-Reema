package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name should not be EMPTY!")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Description should not be EMPTY!")
    @Column(columnDefinition = "varchar(500) not null")
    private String description;

    @NotEmpty(message = "learning Method should not be EMPTY!")
    @Column(columnDefinition = "varchar(20) not null")
    private String learningMethod; //session type

    @NotEmpty(message = "Subject should not be EMPTY!")
    @Column(columnDefinition = "varchar(20) not null")
    private String subject;


    @NotNull(message = "Duration should not be null!")
    @Column(columnDefinition = "DOUBLE not null")
    private double duration; // In min,hours

    @NotNull(message = "Price should not be null!")
    @Column(columnDefinition = "DOUBLE not null")
    private double price;


    //Relations

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private Set<Student> students;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private List<Session> sessions;

    //one course have many reviews
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
    private Set<Review> review;


}
