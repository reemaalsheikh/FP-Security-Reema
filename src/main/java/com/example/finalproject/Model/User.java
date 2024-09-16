package com.example.finalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

//        @NotEmpty(message = "name should not be empty!")
//        @Size(min=2 , max = 20, message = "Length must be between 2 and 20 characters")
//        @Column(columnDefinition = "varchar(50) not null")
//        private String name;

        @NotEmpty(message = "username should not be EMPTY!")
        @Column(columnDefinition = "varchar(100) not null unique")
        private String username;


        @NotEmpty(message = "password should not be empty!")
        // @Size(min=6 , message = "Length must be at least 6 characters")
        @Column(columnDefinition = "varchar(100) not null")
        private String password;


        @Email
        @Column(columnDefinition = "varchar(50) not null unique")
        private String email;

        @NotNull(message = "Age should not be Null!")
       // @Min(value = 18, message = "Age must be at least 18")
        @Column(columnDefinition = "int not null")
        private int age;

        @NotNull(message = "Date of birth date should not be Empty!")
        @JsonFormat(pattern= "yyyy-MM-dd")
        @PastOrPresent
        @Column(columnDefinition = "datetime not null")
        private LocalDate dateOfBirth;


        @NotEmpty(message = "Gender should not be Empty!")
        @Pattern(regexp = "^(Male|Female)$", message = "Two valid inputs only, Male or Female" )
        //@Column(columnDefinition = "varchar(20) not null check(role='Male' or role='Female')")
        private String gender;


        @NotEmpty(message = "Role should not be EMPTY!")
        @Pattern(regexp = "^(STUDENT|TUTOR|ADMIN)$", message = "Role has 3 valid inputs only(STUDENT,TUTOR,ADMIN).")
        //@Column(columnDefinition = "varchar(15) check (role='ADMIN' or role='TUTOR' or role='STUDENT')")
        private String role;

        @NotEmpty(message = "Education level should not be EMPTY!")
        @Column(columnDefinition = "varchar(20) not null")
        private String education_level; //UnderGraduate


        @Column(columnDefinition = "datetime default (current_timestamp)")
        private LocalDate registrationDate;

        //updated_at: Timestamp for the last profile update.
        @Column(columnDefinition = "datetime default (current_timestamp)")
        private LocalDate updatedAt;



        //Relations

        @OneToOne(cascade = CascadeType.ALL , mappedBy ="user" )
        @PrimaryKeyJoinColumn
        private Student student;


        @OneToOne(cascade = CascadeType.ALL , mappedBy ="user" )
        @PrimaryKeyJoinColumn
        private Tutor tutor;






        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority(this.role));
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

}


