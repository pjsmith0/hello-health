package pjs.dataproviders;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import pjs.model.visit.Visit;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@ApplicationScoped
public class VisitRepository {

    private final EntityManager entityManager;

    public VisitRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Visit get(UUID visitId) {
        return entityManager.find(Visit.class, visitId);
    }

    public void save(Visit visit) {
        entityManager.persist(visit);
    }

    public void delete(Visit visit) {
        entityManager.remove(visit);
    }

    public Stream<Visit> stream(LocalDate date) {
        return entityManager.createQuery("""
                    select v from Visit v 
                    left join TimeSlot ts 
                    where ts.date = :slotDate
                """, Visit.class)
                .setParameter("slotDate", date)
                .getResultStream();
    }
}
