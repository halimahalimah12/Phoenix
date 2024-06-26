package com.indocyber.Phoenix.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Administrators")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
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

    @Column(name = "JobTitle")
    private String jobTitle;
}
