package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(max = 30)
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "location can't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @NotEmpty(message = "description can't be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String description;

    @NotNull
    @Positive
    @Column(columnDefinition = "int not null")
    private int capacity;


    //Relations

    @ManyToMany(mappedBy = "clubs")
    @JsonIgnore
    private List<Student> students;



    @ManyToOne
    @JoinColumn(name = "leader_id")
    private Student leader;  // Student who created or leads the club

}
