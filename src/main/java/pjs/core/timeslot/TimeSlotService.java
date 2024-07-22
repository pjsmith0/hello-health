package pjs.core.timeslot;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pjs.dataproviders.TimeSlotRepository;
import pjs.model.timeslot.TimeSlot;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Transactional
    public TimeSlot get(UUID timeSlotId) {
        return timeSlotRepository.get(timeSlotId);
    }

    @Transactional
    public List<TimeSlot> listByDate(LocalDate slotDate) {
        return timeSlotRepository.listAvailableSlots(slotDate);
    }

    @Transactional
    public List<TimeSlot> generateTimeSlots(LocalDate of) {
        return timeSlotRepository.generateTimeSlots(of);
    }
}
