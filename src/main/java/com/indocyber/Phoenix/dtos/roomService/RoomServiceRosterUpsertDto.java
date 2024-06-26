package com.indocyber.Phoenix.dtos.roomService;

import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Builder
@Data
public class RoomServiceRosterUpsertDto {
    private String employeeNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String company;
    private LocalTime mondayRosterStart;
    private LocalTime mondayRosterFinish;
    private LocalTime tuesdayRosterStart;
    private LocalTime tuesdayRosterFinish;
    private LocalTime wednesdayRosterStart;
    private LocalTime wednesdayRosterFinish;
    private LocalTime thursdayRosterStart;
    private LocalTime thursdayRosterFinish;
    private LocalTime fridayRosterStart;
    private LocalTime fridayRosterFinish;
    private LocalTime saturdayRosterStart;
    private LocalTime saturdayRosterFinish;
    private LocalTime sundayRosterStart;
    private LocalTime sundayRosterFinish;
}
