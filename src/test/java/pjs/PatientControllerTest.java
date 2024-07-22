package pjs;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import pjs.core.patient.PatientService;
import pjs.model.patient.Patient;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class PatientControllerTest {

    @Inject
    private PatientService patientService;

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


    @Test
    void testListPatients() {

        given()
                .contentType("application/json")
                .when()
                .get("/patients")
                .then()
                .assertThat()
                .statusCode(200)
                .body("list.size()", equalTo(3));

    }
}
