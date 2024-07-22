package pjs.entrypoints;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import pjs.dataproviders.TimeSlotRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/timeslots")
public class TimeSlotsController {

    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotsController(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public void generate(@QueryParam("date") String dateString) {
        timeSlotRepository.generateTimeSlots(LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE));
    }

}
