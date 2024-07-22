package pjs.dataproviders;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import pjs.model.timeslot.TimeSlot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TimeSlotRepository {
    public static final int TIMESLOT_DURATION_MINUTES = 15;

    private final EntityManager entityManager;

    public TimeSlotRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TimeSlot get(UUID timeSlotId) {
        return entityManager.find(TimeSlot.class, timeSlotId);
    }

    public List<TimeSlot> generateTimeSlots(LocalDate genDate) {
        // TODO: create a better timeslot generator

        LocalDateTime midBreakPoint = LocalDateTime.of(genDate.getYear(), genDate.getMonthValue(), genDate.getDayOfMonth(), 12, 0);
        LocalDateTime nightBreakPoint = LocalDateTime.of(genDate.getYear(), genDate.getMonthValue(), genDate.getDayOfMonth(), 18, 0);

        // from 9 to 12
        LocalDateTime genTime = LocalDateTime.of(genDate.getYear(), genDate.getMonthValue(), genDate.getDayOfMonth(), 9, 0);
        while (genTime.isBefore(midBreakPoint)) {
            entityManager.persist(TimeSlot.builder()
                    .date(genDate)
                    .time(genTime.toLocalTime())
                    .duration(TIMESLOT_DURATION_MINUTES).build());
            genTime = genTime.plusMinutes(TIMESLOT_DURATION_MINUTES);
        }

        // from 14 to 18
        genTime = LocalDateTime.of(genDate.getYear(), genDate.getMonthValue(), genDate.getDayOfMonth(), 14, 0);
        while (genTime.isBefore(nightBreakPoint)) {
            entityManager.persist(TimeSlot.builder()
                    .date(genDate)
                    .time(genTime.toLocalTime())
                    .duration(TIMESLOT_DURATION_MINUTES).build());
            genTime = genTime.plusMinutes(TIMESLOT_DURATION_MINUTES);
        }

        return entityManager.createQuery("""
            select ts from TimeSlot ts
                where ts.date = :slotDate
        """, TimeSlot.class)
                .setParameter("slotDate", genDate)
                .getResultStream()
                .toList();
    }

    public List<TimeSlot> listTimeSlots(LocalDate slotDate) {
        return entityManager.createQuery("""
                    select ts from TimeSlot ts 
                    left join Visit v 
                    where ts.date = :slotDate
                """, TimeSlot.class)
                .setParameter("slotDate", slotDate)
                .getResultList();

    }

    public List<TimeSlot> listAvailableSlots(LocalDate slotDate) {
        return entityManager.createQuery("""
                    select ts from TimeSlot ts 
                    left join Visit v 
                    where  v.id is null and
                    ts.date = :slotDate
                """, TimeSlot.class)
                .setParameter("slotDate", slotDate)
                .getResultList();

    }
}
