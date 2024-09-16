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
public class FaceToFace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String location;

    @NotNull
    @Positive
    private double price;


//Relations
  //omar
//    @OneToOne
//    @JoinColumn(name = "session_id")
//    private Session session;

  //reema
    @OneToOne
    @MapsId
    @JsonIgnore
    private Session session;

    //Omar
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

}

