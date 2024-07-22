package pjs.model.visit.dto;

import lombok.Builder;
import lombok.Data;
import pjs.model.visit.VisitReason;
import pjs.model.visit.VisitType;

import java.util.UUID;

@Data
@Builder
public class CreateVisitDTO {
    private UUID patientId;
    private UUID timeSlotId;
    private VisitType visitType;
    private VisitReason visitReason;
    private String familyHistory;
}
