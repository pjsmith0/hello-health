package pjs.model.visit;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pjs.model.patient.Patient;
import pjs.model.patient.PatientMapper;
import pjs.model.timeslot.TimeSlot;
import pjs.model.timeslot.TimeSlotMapper;
import pjs.model.visit.dto.CreateVisitDTO;
import pjs.model.visit.dto.VisitDTO;
import pjs.model.visit.dto.UpdateVisitDTO;

@Mapper(componentModel = "cdi", uses = {PatientMapper.class, TimeSlotMapper.class})
public interface VisitMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", source = "patient")
    @Mapping(target = "timeSlot", source = "timeSlot")
    Visit createToEntity(CreateVisitDTO dto, Patient patient, TimeSlot timeSlot);

    @Mapping(target = "id", source = "dto.id")
    @Mapping(target = "patient", source = "patient")
    @Mapping(target = "timeSlot", source = "timeSlot")
    Visit updateToEntity(UpdateVisitDTO dto, Patient patient, TimeSlot timeSlot);

    VisitDTO map(Visit visit);

}
