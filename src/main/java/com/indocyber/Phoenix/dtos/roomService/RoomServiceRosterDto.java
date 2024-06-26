package com.indocyber.Phoenix.dtos.roomService;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;

@Data
@Builder
public class RoomServiceRosterDto {
    private String employeeNumber;
    private String fullName;
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
