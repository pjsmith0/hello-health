package pjs.model.timeslot;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TimeSlotMapper {

    TimeSlotDTO toDTO(TimeSlot timeSlot);

}
