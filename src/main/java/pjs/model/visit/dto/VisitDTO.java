package pjs.model.visit.dto;

import lombok.Data;
import pjs.model.patient.PatientDTO;
import pjs.model.timeslot.TimeSlotDTO;
import pjs.model.visit.VisitReason;
import pjs.model.visit.VisitType;

import java.util.UUID;

@Data
public class VisitDTO {
    private UUID id;
    private PatientDTO patient;
    private TimeSlotDTO timeSlot;
    private VisitType visitType;
    private VisitReason visitReason;
    private String familyHistory;
}
