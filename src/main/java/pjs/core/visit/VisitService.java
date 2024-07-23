package pjs.core.visit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import pjs.core.patient.PatientService;
import pjs.core.timeslot.TimeSlotService;
import pjs.dataproviders.VisitRepository;
import pjs.model.patient.Patient;
import pjs.model.timeslot.TimeSlot;
import pjs.model.visit.Visit;
import pjs.model.visit.VisitMapper;
import pjs.model.visit.dto.CreateVisitDTO;
import pjs.model.visit.dto.UpdateVisitDTO;
import pjs.model.visit.dto.VisitDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VisitService {

    private final PatientService patientService;
    private final TimeSlotService timeSlotService;
    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    public VisitService(PatientService patientService, TimeSlotService timeSlotService, VisitRepository visitRepository, VisitMapper visitMapper) {
        this.patientService = patientService;
        this.timeSlotService = timeSlotService;
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
    }

    @Transactional
    public VisitDTO create(CreateVisitDTO createVisitDTO) {
        Patient patient = patientService.get(createVisitDTO.getPatientId());
        TimeSlot timeSlot = timeSlotService.get(createVisitDTO.getTimeSlotId());

        Visit newVisit = visitMapper.createToEntity(createVisitDTO, patient, timeSlot);
        visitRepository.save(newVisit);

        return visitMapper.map(newVisit);
    }

    @Transactional
    public VisitDTO update(UpdateVisitDTO updateVisitDTO) {
        Visit visit = visitRepository.get(updateVisitDTO.getId());

        visit.setVisitType(updateVisitDTO.getVisitType());
        visit.setVisitReason(updateVisitDTO.getVisitReason());
        visit.setFamilyHistory(updateVisitDTO.getFamilyHistory());

        if (visit.getPatient().getId() != updateVisitDTO.getPatientId()) {
            Patient patient = patientService.get(updateVisitDTO.getPatientId());
            visit.setPatient(patient);
        }

        if (visit.getTimeSlot().getId() != updateVisitDTO.getTimeSlotId()) {
            TimeSlot timeSlot = timeSlotService.get(updateVisitDTO.getTimeSlotId());
            visit.setTimeSlot(timeSlot);
        }

        visitRepository.save(visit);

        return visitMapper.map(visit);
    }

    @Transactional
    public void delete(UUID visitId) {
        Visit visitToBeDeleted = visitRepository.get(visitId);
        visitRepository.delete(visitToBeDeleted);
    }

    @Transactional
    public Visit get(UUID visitId) {
        return visitRepository.get(visitId);
    }

    @Transactional
    public List<VisitDTO> list(LocalDate date) {
        return visitRepository
                .stream(date)
                .map(visitMapper::map)
                .toList();
    }
}
