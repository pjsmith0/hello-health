package pjs.core.patient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pjs.dataproviders.PatientRepository;
import pjs.model.patient.Patient;
import pjs.model.patient.PatientDTO;
import pjs.model.patient.PatientMapper;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PatientService {

    private PatientRepository patientRepository;
    private PatientMapper mapper;

    public PatientService(PatientRepository patientRepository, PatientMapper mapper) {
        this.patientRepository = patientRepository;
        this.mapper = mapper;
    }

    @Transactional
    public Patient get(UUID patientId) {
        return patientRepository.get(patientId);
    }

    @Transactional
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public List<PatientDTO> getPatients() {
        return patientRepository
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
