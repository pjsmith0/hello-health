package pjs.model.patient;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "id", name = "patient_id_hidx"),
        @Index(columnList = "socialsecuritynumber", name = "socialsecuritynumber_hidx")
})
public class Patient {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    private String socialSecurityNumber;
}
