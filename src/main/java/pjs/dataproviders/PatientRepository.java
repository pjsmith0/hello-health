package pjs.dataproviders;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import pjs.model.patient.Patient;

import java.util.UUID;
import java.util.stream.Stream;

@ApplicationScoped
public class PatientRepository {

    private final EntityManager entityManager;

    public PatientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Patient get(UUID patientId) {
        return entityManager.find(Patient.class, patientId);
    }

    public Patient save(Patient patient) {
        entityManager.persist(patient);

        return patient;
    }

    public Stream<Patient> stream() {
        return entityManager.createQuery("""
            select p from Patient p 
            order by p.surname
        """).getResultStream();
    }
}
