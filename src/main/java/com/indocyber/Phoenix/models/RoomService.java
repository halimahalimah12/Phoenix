package com.indocyber.Phoenix.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "RoomServices")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomService {
    @Id
    @Column(name = "EmployeeNumber")
    private String employeeNumber;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "MiddleName")
    private String middleName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "OutsoutchingCompany")
    private String outsourchingCompany;
    @Column(name = "MondayRosterStart")
    private LocalTime mondayRosterStart;
    @Column(name = "MondayRosterFinish")
    private LocalTime mondayRosterFinish;
    @Column(name = "TuesdayRosterStart")
    private LocalTime tuesdayRosterStart;
    @Column(name = "TuesdayRosterFinish")
    private LocalTime tuesdayRosterFinish;
    @Column(name = "WednesdayRosterStart")
    private LocalTime wednesdayRosterStart;
    @Column(name = "WednesdayRosterFinish")
    private LocalTime wednesdayRosterFinish;
    @Column(name = "ThursdayRosterStart")
    private LocalTime thursdayRosterStart;
    @Column(name = "ThursdayRosterFinish")
    private LocalTime thursdayRosterFinish;
    @Column(name = "FridayRosterStart")
    private LocalTime fridayRosterStart;
    @Column(name = "FridayRosterFinish")
    private LocalTime fridayRosterFinish;
    @Column(name = "SaturdayRosterStart")
    private LocalTime saturdayRosterStart;
    @Column(name = "SaturdayRosterFinish")
    private LocalTime saturdayRosterFinish;
    @Column(name = "SundayRosterStart")
    private LocalTime sundayRosterStart;
    @Column(name = "SundayRosterFinish")
    private LocalTime sundayRosterFinish;
}
