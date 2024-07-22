package pjs.model.patient;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PatientDTO {
    private UUID id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String socialSecurityNumber;
}
