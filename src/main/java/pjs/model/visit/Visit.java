package pjs.model.visit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pjs.model.patient.Patient;
import pjs.model.timeslot.TimeSlot;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private TimeSlot timeSlot;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Patient patient;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VisitType visitType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VisitReason visitReason;

    @Column(columnDefinition = "text")
    private String familyHistory;

}
