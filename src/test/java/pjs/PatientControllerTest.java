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

    @Test
    void testListPatients() {

        given()
                .contentType("application/json")
                .when()
                .get("/patients")
                .then()
                .assertThat()
                .statusCode(200)
                .body("list.size()", equalTo(10));

    }
}
