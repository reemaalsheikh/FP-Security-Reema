package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZoomMeeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingId;

    @NotEmpty(message = "Url should not be empty!")
    private String url;

    @NotNull(message = "Price should not be Null!")
    private double price;

//    //add a
//    //@Column(columnDefinition = "datetime default (current_timestamp)")
//    @NotNull(message = "Date of birth date should not be Empty!")
//    @JsonFormat(pattern= "yyyy-MM-dd")
//    @FutureOrPresent
//    @Column(columnDefinition = "datetime not null")
//    private LocalDate meetingDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date meetingDate;


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


    //reema  to let the student assign to Zoom meeting
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;




}
