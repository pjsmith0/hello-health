package pjs.entrypoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import pjs.core.patient.PatientService;
import pjs.model.patient.PatientDTO;

import java.util.List;

@Path("/")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("patients")
    public List<PatientDTO> list() {
        return patientService.getPatients();
    }
}
