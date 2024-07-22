package pjs.model.timeslot;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimeSlot {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private LocalDate date;

    private LocalTime time;

    private int duration;

}
