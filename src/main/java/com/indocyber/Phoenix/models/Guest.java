package com.indocyber.Phoenix.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Guests")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Guest {
//    @Id
//    @Column(name = "Id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    @OneToOne
//    @JoinColumn(name = "AccountId")
//    private Account account;
    @Id
    @Column(name="Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name="Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "Citizenship")
    private String citizenship;
    @Column(name = "IdNumber")
    private String idNumber;


}
