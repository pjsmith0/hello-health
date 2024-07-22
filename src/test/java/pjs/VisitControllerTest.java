package pjs;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pjs.core.patient.PatientService;
import pjs.core.timeslot.TimeSlotService;
import pjs.core.visit.VisitService;
import pjs.model.patient.Patient;
import pjs.model.timeslot.TimeSlot;
import pjs.model.visit.Visit;
import pjs.model.visit.VisitReason;
import pjs.model.visit.VisitType;
import pjs.model.visit.dto.CreateVisitDTO;
import pjs.model.visit.dto.UpdateVisitDTO;
import pjs.model.visit.dto.VisitDTO;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class VisitControllerTest {

    @Inject
    private PatientService patientService;

    @Inject
    private TimeSlotService timeSlotService;

    @Inject
    private VisitService visitService;

    private List<Patient> patients = List.of(
            Patient.builder()
                    .surname("Johnson")
                    .name("John")
                    .dateOfBirth(LocalDate.of(1990, 7, 10))
                    .socialSecurityNumber("009-84-3389")
                    .build(),
            Patient.builder()
                    .surname("Williams")
                    .name("Mary")
                    .dateOfBirth(LocalDate.of(2002, 8, 2))
                    .socialSecurityNumber("433-71-0782")
                    .build(),
            Patient.builder()
                    .surname("Brown")
                    .name("Michael")
                    .dateOfBirth(LocalDate.of(1999, 10, 4))
                    .socialSecurityNumber("576-32-1375")
                    .build()
    );

    private List<TimeSlot> timeSlots;

    @BeforeEach
    void before() {
        for (Patient patient : patients) {
            patientService.save(patient);
        }

        timeSlots = timeSlotService.generateTimeSlots(LocalDate.of(2024, 1, 10));

    }

    @Test
    void testCreateVisit() {

        given()
                .contentType("application/json")  //another way to specify content type
                .body(CreateVisitDTO.builder()
                        .patientId(patients.get(0).getId())
                        .timeSlotId(timeSlots.get(0).getId())
                        .visitType(VisitType.HOME)
                        .visitReason(VisitReason.RECURRING_VISIT)
                        .familyHistory("")
                        .build()
                )
                .when()
                .post("/visit")
                .then()
                .assertThat()
                .statusCode(200)
                .body("visitType", is("HOME"))
                .body("visitReason", is("RECURRING_VISIT"))
                .body("patient.id", is(patients.get(0).getId().toString()))
                .body("timeSlot.id", is(timeSlots.get(0).getId().toString()))
        ;
    }

    @Test
    void testUpdateVisit() {

        VisitDTO visitDTO = visitService.create(CreateVisitDTO.builder()
                .patientId(patients.get(1).getId())
                .timeSlotId(timeSlots.get(1).getId())
                .visitType(VisitType.HOME)
                .visitReason(VisitReason.RECURRING_VISIT)
                .build());

        given()
                .contentType("application/json")
                .body(UpdateVisitDTO.builder()
                        .id(visitDTO.getId())
                        .patientId(visitDTO.getPatient().getId())
                        .timeSlotId(visitDTO.getTimeSlot().getId())
                        .visitType(VisitType.HOME)
                        .visitReason(VisitReason.RECURRING_VISIT)
                        .familyHistory("")
                        .build()
                )
                .when()
                .put("/visit")
                .then()
                .assertThat()
                .statusCode(200)
                .body("visitType", is("HOME"))
                .body("visitReason", is("RECURRING_VISIT"))
                .body("patient.id", is(patients.get(0).getId().toString()))
                .body("timeSlot.id", is(timeSlots.get(0).getId().toString()))
        ;
    }

    @Test
    void testDeleteVisit() {

        VisitDTO createdVisit = visitService.create(CreateVisitDTO.builder()
                .patientId(patients.get(1).getId())
                .timeSlotId(timeSlots.get(1).getId())
                .visitType(VisitType.HOME)
                .visitReason(VisitReason.RECURRING_VISIT)
                .build());

        given()
                .contentType("application/json")  //another way to specify content type
                .when()
                .delete("/visit/" + createdVisit.getId().toString())
                .then()
                .assertThat()
                .statusCode(200)
        ;

        Visit deletedVisit = visitService.get(createdVisit.getId());
        assertThat(deletedVisit, is(nullValue()));
    }

}
