package pjs.model.patient;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);
}
