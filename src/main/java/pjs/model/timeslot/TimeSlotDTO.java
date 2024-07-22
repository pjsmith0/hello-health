package pjs.model.timeslot;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class TimeSlotDTO {
    private UUID id;
    private LocalDate date;
    private LocalTime time;
}
